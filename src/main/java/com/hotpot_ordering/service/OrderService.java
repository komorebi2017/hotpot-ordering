package com.hotpot_ordering.service;

import com.hotpot_ordering.dataObject.OrderMaster;
import com.hotpot_ordering.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 19:13
 * @ Description:
 */
public interface OrderService {

    /*创建订单*/
    OrderDTO create(OrderDTO orderDTO);

    /*查询单个订单*/
    OrderDTO findOne(String orderId);

    /*查询订单列表*/
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /*取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);

    /*完结订单*/
    OrderDTO finish(OrderDTO orderDTO);

    /*支付订单*/
    OrderDTO paid(OrderDTO orderDTO);

    /*查询订单列表,查询所有的*/
    Page<OrderDTO> findList(Pageable pageable);

}
