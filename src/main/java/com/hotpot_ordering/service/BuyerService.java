package com.hotpot_ordering.service;

import com.hotpot_ordering.dto.OrderDTO;
import org.hibernate.criterion.Order;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/3 8:11
 * @ Description:
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderid);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderid);
}

