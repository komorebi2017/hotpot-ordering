package com.hotpot_ordering.service;

import com.hotpot_ordering.dataObject.ProductCategory;

import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 8:59
 * @ Description:
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
