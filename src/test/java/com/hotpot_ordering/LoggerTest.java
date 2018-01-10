package com.hotpot_ordering;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.slf4j.Logger;


/**
 * @ Author: 陌北有棵树
 * @ Date: 2017/12/31 20:37
 * @ Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        String name = "hhy";
        String password = "123456";
        /*logger.error("error");
        logger.info("info");*/
        logger.info("name:"+ name + "password:" + password);
        logger.info("name: {}, password:{}",name,password);
    }
}
