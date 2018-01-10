package com.hotpot_ordering.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/8 19:07
 * @ Description:
 */
public class CookieUtil {

    /**
     *@ Author:陌北有棵树
     *@ Date: 2018/1/8 19:09
     *@ Description: 设置cookie
     *@ Param:  HttpServletResponse response,
                String name,
                String value,
                int maxAge
     *@ Return:
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge){
        Cookie cookie = new Cookie("token",value);
        cookie.setPath("/");
        cookie.setMaxAge(7200);
        response.addCookie(cookie);

    }
    /**
     *@ Author:陌北有棵树
     *@ Date: 2018/1/8 19:34
     *@ Description: 获取Cookie
     *@ Param:  HttpServletRequest request,
                String name
     *@ Return:
     */
    public static Cookie get(HttpServletRequest request,
                           String name){

        Map<String, Cookie> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name)){
            return  cookieMap.get(name);
        }else {
            return null;
        }

    }


    /**
     *@ Author:陌北有棵树
     *@ Date: 2018/1/8 19:27
     *@ Description: 将Cookie封装成Map，获得的cookie是一个数组形式，要转换成map格式
     *@ Param:
     *@ Return:
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Map<String, Cookie> cookieMap = new HashMap<>();
        if(cookies != null){
            for (Cookie cookie: cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
