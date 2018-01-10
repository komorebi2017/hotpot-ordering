package com.hotpot_ordering.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 18:32
 * @ Description: 订单详情
 */
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

}
