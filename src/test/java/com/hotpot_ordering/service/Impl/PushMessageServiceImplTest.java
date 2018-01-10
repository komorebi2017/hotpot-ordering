package com.hotpot_ordering.service.Impl;

import com.hotpot_ordering.dto.OrderDTO;
import com.hotpot_ordering.service.OrderService;
import com.hotpot_ordering.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/8 20:49
 * @ Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {
    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1514897198837888489");
        pushMessageService.orderStatus(orderDTO);

    }

}