package com.hotpot_ordering.controller;

import com.hotpot_ordering.VO.ResultVO;
import com.hotpot_ordering.converter.OrderFormToOrderDTOConverter;
import com.hotpot_ordering.dto.OrderDTO;
import com.hotpot_ordering.enums.OrderStatusEnum;
import com.hotpot_ordering.enums.ResultEnum;
import com.hotpot_ordering.exception.SellException;
import com.hotpot_ordering.form.OrderForm;
import com.hotpot_ordering.service.BuyerService;
import com.hotpot_ordering.service.OrderService;
import com.hotpot_ordering.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 22:01
 * @ Description:
 */
@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("创建订单：参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("创建订单：购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);

    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<OrderDTO> list(@RequestParam("openid") String openid,
                                   @RequestParam(value = "page",defaultValue = "0") Integer page,
                                   @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("查询订单列表：openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage =orderService.findList(openid,request);

        //转存 Date-> Long

        return ResultVOUtil.success(orderDTOPage.getContent());

    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderid") String orderid){

        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderid);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderid") String orderid){

        buyerService.cancelOrder(openid,orderid);
        return ResultVOUtil.success();
    }
}
