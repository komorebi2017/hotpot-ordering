package com.hotpot_ordering.form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/5 20:22
 * @ Description: 存储表单提交的字段
 */
@Data
public class ProductForm {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
