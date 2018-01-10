package com.hotpot_ordering.enums;

import lombok.Getter;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 18:27
 * @ Description:
 */

@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0,"未支付"),
    SUCCESS(1,"支付成功");

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
