package com.hotpot_ordering.repository;

import com.hotpot_ordering.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 8:03
 * @ Description:
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
