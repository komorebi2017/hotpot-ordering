package com.hotpot_ordering.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 20:00
 * @ Description: 前端商品
 */

@Data
public class ProductVO {

    /*因为前端要求的是“name”，但是起名字的表意最好明确*/
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;


    /*并不是ProductInfo，因为前端需要几个字段，就给他返回几个字段，
      是出于安全和隐私的考虑，*/
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList ;


}
