package com.hotpot_ordering.dataObject;

import com.hotpot_ordering.enums.OrderStatusEnum;
import com.hotpot_ordering.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 18:19
 * @ Description: 订单主表
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /*订单状态，默认为0，新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /*支付状态，默认为0，,未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;






}
