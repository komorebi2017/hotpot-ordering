package com.hotpot_ordering.enums;

import lombok.Getter;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 10:01
 * @ Description: 商品状态
 */

@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0,"在架"),
    DOWM(1,"下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
