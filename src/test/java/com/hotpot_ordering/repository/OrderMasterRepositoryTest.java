package com.hotpot_ordering.repository;

import com.hotpot_ordering.dataObject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 18:40
 * @ Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest

public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "111111";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("233456");
        orderMaster.setBuyerName("Tom");
        orderMaster.setBuyerPhone("13112365412");
        orderMaster.setBuyerAddress("吉林市");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(23));
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {

        /*PageRequest继承了一个抽象类AbstractPageRequest
        * AbstractPageRequest实现了Pageable接口*/

        PageRequest request = new PageRequest(0,3);

        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID,request);
        Assert.assertNotEquals(0,result.getTotalElements());
        System.out.println(result.getTotalElements());


    }

}