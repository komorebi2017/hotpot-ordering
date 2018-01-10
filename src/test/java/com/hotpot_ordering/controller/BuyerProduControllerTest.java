package com.hotpot_ordering.controller;

import com.hotpot_ordering.VO.ResultVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 20:55
 * @ Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerProduControllerTest {

    @Autowired
    private BuyerProduController buyerProduController;

    @Test
    public void list() throws Exception {
        ResultVO resultVO = buyerProduController.list();
        System.out.println(resultVO);
    }

}