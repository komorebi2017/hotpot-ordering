package com.hotpot_ordering.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.hotpot_ordering.dataObject.SellerInfo;
import com.hotpot_ordering.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/5 21:37
 * @ Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void findByOpenid() throws Exception {
        SellerInfo sellerInfo = repository.findByOpenid("abc");
        Assert.assertEquals("abc",sellerInfo.getOpenid());
    }

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("123");
        sellerInfo.setOpenid("abc");

        SellerInfo result = repository.save(sellerInfo);
        Assert.assertNotNull(result);
    }

}