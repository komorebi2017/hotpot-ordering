package com.hotpot_ordering.service.Impl;

import com.hotpot_ordering.dataObject.OrderDetail;
import com.hotpot_ordering.dto.OrderDTO;
import com.hotpot_ordering.enums.OrderStatusEnum;
import com.hotpot_ordering.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 20:18
 * @ Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "11055110";

    private final String ORDER_ID = "15148988866666151841";

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("Mary");
        orderDTO.setBuyerAddress("地球");
        orderDTO.setBuyerPhone("18899999999");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList =new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("54321");
        o1.setProductQuantity(2);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("23458");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("创建订单 result={}",result);
        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() throws Exception {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("查询单个订单 result={}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID,request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCLE.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());

    }

    @Test
    public void list(){
        PageRequest request = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
       // Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
        Assert.assertTrue("查询所有的订单列表",orderDTOPage.getTotalElements()>0);
    }

}