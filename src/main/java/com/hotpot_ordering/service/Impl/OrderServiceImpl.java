package com.hotpot_ordering.service.Impl;

import com.hotpot_ordering.converter.OrderMasterToOrderDTOConverter;
import com.hotpot_ordering.dataObject.OrderDetail;
import com.hotpot_ordering.dataObject.OrderMaster;
import com.hotpot_ordering.dataObject.ProductInfo;
import com.hotpot_ordering.dto.CartDTO;
import com.hotpot_ordering.dto.OrderDTO;
import com.hotpot_ordering.enums.OrderStatusEnum;
import com.hotpot_ordering.enums.PayStatusEnum;
import com.hotpot_ordering.enums.ResultEnum;
import com.hotpot_ordering.exception.SellException;
import com.hotpot_ordering.repository.OrderDetailRepository;
import com.hotpot_ordering.repository.OrderMasterRepository;
import com.hotpot_ordering.service.OrderService;
import com.hotpot_ordering.service.ProductService;
import com.hotpot_ordering.service.PushMessageService;
import com.hotpot_ordering.service.WebSocket;
import com.hotpot_ordering.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 19:22
 * @ Description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{


    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private WebSocket webSocket;


    @Override
    @Transactional
     /*注意有些信息不用从前端传
    * 有些信息一定不能从前端传，单价*/
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAccount = new BigDecimal(BigInteger.ZERO);

        //1.查询商品：数量，价格
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2.计算订单的总价
            orderAccount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAccount);


            //3.写入订单数据库：orderMaster,orderDetail两个表
            /*订单详情orderDetail入库*/

            /*orderDetail.setProductName(productInfo.getProductName());
            * 如果每个属性都如上set会写很多，Spring提供了一个方法
            * 进行对象的属性拷贝
            * 不过属性拷贝时注意：属性是null也会被拷贝进去
            * 解决办法是先拷贝，然后再设置*/
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);

            /*订单orderMaster入库*/
            OrderMaster orderMaster = new OrderMaster();

            orderDTO.setOrderId(orderId); // 修改（1）后
            BeanUtils.copyProperties(orderDTO,orderMaster);

            /*这里只会设置orderMaster的orderId，orderDTO的orderDTO肯定是null
            orderMaster.setOrderId(orderId);
            所以做了修改（1）*/
            orderMaster.setOrderAmount(orderAccount);
            orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
            orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
            orderMasterRepository.save(orderMaster);
        }

        //4.扣库存,注意超卖问题
        /*使用lambda表达式可以一次性使用toList直接返回一个list
        * 就不用在上面的循环里面加一些与计算总价和详情入库无关的内容了*/

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e->
                new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);


        //发送websocket消息
        webSocket.sendMessage(orderDTO.getOrderId());

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage=
                orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);

        List<OrderDTO>orderDTOList =
                OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage =
                new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();


        //1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("取消订单：订单状态不正确，orderId={},orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2.修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCLE.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("取消订单：更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //3.返还库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("取消订单：订单中没有商品，orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<CartDTO> cartDTOList =
                orderDTO.getOrderDetailList().stream().map(e ->
                        new CartDTO(e.getProductId(), e.getProductQuantity()))
                        .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        //4.如果已支付需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        //1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("完结订单：订单状态不正确， orderId={},orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("完结订单：更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //推送模板消息
         pushMessageService.orderStatus(orderDTO);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("支付订单：订单状态不正确， orderId={},orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2.判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("支付订单：订单支付状态不正确，orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //3.修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("支付订单：更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO>orderDTOList =
                OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage =
                new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }
}
