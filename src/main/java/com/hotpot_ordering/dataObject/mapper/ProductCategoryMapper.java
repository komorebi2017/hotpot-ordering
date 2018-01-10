package com.hotpot_ordering.dataObject.mapper;

import com.hotpot_ordering.dataObject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.Map;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/8 22:10
 * @ Description:
 */
@Mapper
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name, category_type) values (#{category_name, jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);


    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    // 数据库字段和变量的映射
    ProductCategory findByCategoryType(Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,
                             @Param("categoryType") Integer categoryType);


    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);
}
