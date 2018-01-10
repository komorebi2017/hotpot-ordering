package com.hotpot_ordering.enums;

import lombok.Getter;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 18:23
 * @ Description:
 */

@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCLE(2,"已取消");

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
