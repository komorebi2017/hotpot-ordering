package com.hotpot_ordering.dto;

import lombok.Data;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 19:58
 * @ Description: 购物车
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
