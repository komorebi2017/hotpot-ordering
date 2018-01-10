package com.hotpot_ordering.service.Impl;

import com.hotpot_ordering.dataObject.SellerInfo;
import com.hotpot_ordering.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/5 21:46
 * @ Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {

    private final static String openid = "abc";

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoByOpenid() throws Exception {
        SellerInfo result = sellerService.findSellerInfoByOpenid(openid);
        Assert.assertEquals(openid, result.getOpenid());
    }

}