package com.hotpot_ordering.controller;

import com.hotpot_ordering.config.ProjectUrlConfig;
import com.hotpot_ordering.constant.CookieConstant;
import com.hotpot_ordering.constant.RedisConstant;
import com.hotpot_ordering.dataObject.SellerInfo;
import com.hotpot_ordering.enums.ResultEnum;
import com.hotpot_ordering.service.SellerService;
import com.hotpot_ordering.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/8 18:25
 * @ Description: 卖家用户相关操作
 */
/*涉及页面跳转，页面呈现用Controller*/
@Controller
@RequestMapping("/seller")
public class SellerUserController {


    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map){

        // 1.openid去和数据库里面的数据进行匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null ){
            map.put("msg", ResultEnum.LOGIN_FAIL);
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error");
        }
        // 2.设置token至redis
        // 设置过期时间
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        /*
         *@ Author:陌北有棵树
         *@ Date: 2018/1/8 18:54
         *@ Description: 操作某些value
         *               String.format(RedisConstant.TOKEN_PREFIX)格式化，以TOKEN_PREFIX开头
         *@ Param: redis的key，openid，过期时间，过期时间的单位
         *@ Return:
         */
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token), openid, expire, TimeUnit.SECONDS );

        // 3.设置token至cookie（校验时是从cookie里拿到token，拿到之后再去后端的redis再去校验一次）
        CookieUtil.set(response, CookieConstant.TOKEN ,token,expire);

         /*这里只是进行跳转，而不进行模板的渲染
         * 这里有点疑问，为什么我明明已经设成绝对路径了，还是不能加/sell*/
        return new ModelAndView("redirect:"+"/seller/order/list");
    }



    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String,Object>map){

        // 1.从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie != null){
            // 2.清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            // 3.清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS);
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
