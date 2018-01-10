package com.hotpot_ordering.service;

import com.hotpot_ordering.dto.OrderDTO;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/8 20:38
 * @ Description: 推送消息
 */
public interface PushMessageService {

    /**
     *@ Author:陌北有棵树
     *@ Date: 2018/1/8 20:38
     *@ Description: 订单状态变更消息
     *@ Param:
     *@ Return:
     */
    void orderStatus(OrderDTO orderDTO);
}
