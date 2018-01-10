package com.hotpot_ordering.utils;

import com.hotpot_ordering.enums.CodeEnum;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/3 21:04
 * @ Description:
 */
public class EnumUtil {
    public static <T extends CodeEnum>T getByCode(Integer code, Class<T> enumClass){
        for(T each: enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
