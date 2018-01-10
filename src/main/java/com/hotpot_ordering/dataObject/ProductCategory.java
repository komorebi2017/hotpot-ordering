package com.hotpot_ordering.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 7:54
 * @ Description:类目
 */


/*把数据库映射成对象要加注解*/
@Entity

/*动态更新*/
@DynamicUpdate

/*lombok:包含生成get set toString一些方法*/

@Data

public class ProductCategory {
    @Id  //主键
    @GeneratedValue //自增类型
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
