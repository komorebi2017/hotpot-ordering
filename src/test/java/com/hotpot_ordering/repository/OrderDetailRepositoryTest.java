package com.hotpot_ordering.repository;

import com.hotpot_ordering.dataObject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 19:05
 * @ Description:
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("5433434");
        orderDetail.setOrderId("111155551");
        orderDetail.setProductIcon("https://sssss");
        orderDetail.setProductId("2345666");
        orderDetail.setProductName("鱼丸");
        orderDetail.setProductPrice(new BigDecimal(20));
        orderDetail.setProductQuantity(2);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailList = repository.findByOrderId("111155551");
        Assert.assertNotEquals(0,orderDetailList.size());
    }

}