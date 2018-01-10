package com.hotpot_ordering.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/9 18:37
 * @ Description:
 */

@Commit
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lock(String key, String value){
        /*如果可以设置，返回True，反之返回false*/
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }
        return false;

    }
}
