package com.hotpot_ordering.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 22:04
 * @ Description: 订单的表单验证
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
