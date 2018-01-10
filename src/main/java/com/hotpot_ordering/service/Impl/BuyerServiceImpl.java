package com.hotpot_ordering.service.Impl;

import com.hotpot_ordering.dto.OrderDTO;
import com.hotpot_ordering.enums.ResultEnum;
import com.hotpot_ordering.exception.SellException;
import com.hotpot_ordering.service.BuyerService;
import com.hotpot_ordering.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/3 8:13
 * @ Description:
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderid) {
        return checkOrderOwner(openid, orderid);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderid) {
        OrderDTO orderDTO = checkOrderOwner(openid,orderid);
        if(orderDTO == null){
            log.error("取消订单：查不到该订单，orderid={}",orderid);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }


    private OrderDTO checkOrderOwner(String openid, String orderid){
        OrderDTO orderDTO = orderService.findOne(orderid);
        if(orderDTO == null){
            return null;
        }
        //判断是否是自己的订单
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("查询订单：订单的openID不一致，openid={},orderDTO={}",openid,orderid);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
