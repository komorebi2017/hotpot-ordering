package com.hotpot_ordering.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hotpot_ordering.dataObject.OrderDetail;
import com.hotpot_ordering.enums.OrderStatusEnum;
import com.hotpot_ordering.enums.PayStatusEnum;
import com.hotpot_ordering.utils.EnumUtil;
import com.hotpot_ordering.utils.serializer.DateToLongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 19:18
 * @ Description: 在各个层传输用的Order对象
 */
@Data
   /*
    字段如果是空就不返回
    @JsonInclude(JsonInclude.Include.NON_NULL)
    也可以在配置文件中配全局的
    ps: 如果某些字段是要求必须显示，为空显示空串之类的，通过赋初值的方式来做
   */

public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /*订单状态，默认为0，新下单*/
    private Integer orderStatus;

    /*支付状态，默认为0，,未支付*/
    private Integer payStatus;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;


    /* @JsonIgnore注解： 对象转成Json格式时会忽略掉该方法*/
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

}
