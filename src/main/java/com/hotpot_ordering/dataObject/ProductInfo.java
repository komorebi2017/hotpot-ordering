package com.hotpot_ordering.dataObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotpot_ordering.enums.ProductStatusEnum;
import com.hotpot_ordering.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 9:23
 * @ Description:
 */

@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus = ProductStatusEnum.UP.getCode();

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductInfo() {
    }

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
