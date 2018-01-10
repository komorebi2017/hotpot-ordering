package com.hotpot_ordering.utils;

import java.util.Random;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 19:43
 * @ Description:
 */
public class KeyUtil {

    /**
     *@ Author:陌北有棵树
     *@ Date: 2018/1/2 19:43
     *@ Description: 生成唯一的主键
     *               格式：时间+随机数
     *@ Param:
     *@ Return: 当前的毫秒数 + 一个六位随机数
     */
    public static synchronized String genUniqueKey(){
        Random random =new Random();

        /*生成六位随机数*/
        Integer num = random.nextInt(900000)+100000;

        /*System.currentTimeMillis():当前的毫秒数*/
        return System.currentTimeMillis() + String.valueOf(num);

    }
}
