package com.hotpot_ordering.aspect;

import com.hotpot_ordering.constant.CookieConstant;
import com.hotpot_ordering.constant.RedisConstant;
import com.hotpot_ordering.exception.SellException;
import com.hotpot_ordering.exception.SellerAuthorizeException;
import com.hotpot_ordering.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/8 19:49
 * @ Description: 希望访问的每一个url都进行一个用户信息的验证
 *                所以使用AOP方式做了一个切面
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.hotpot_ordering.controller.Seller*.*(..))" +
            "&& !execution(public * com.hotpot_ordering.controller.SellerUserController.*(..))")
    public void verity(){}


    @Before("verity()")
    public void doVerity(){
        ServletRequestAttributes attributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询Cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null){
            log.warn("登录校验：Cookie中查不到Token");
            throw new SellerAuthorizeException();
        }

        //查询Redis
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.warn("登录校验：Redis中查不到Token");
            throw new SellerAuthorizeException();
        }
    }
}
