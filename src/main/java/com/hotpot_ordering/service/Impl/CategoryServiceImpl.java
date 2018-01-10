package com.hotpot_ordering.service.Impl;

import com.hotpot_ordering.dataObject.ProductCategory;
import com.hotpot_ordering.repository.ProductCategoryRepository;
import com.hotpot_ordering.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 9:01
 * @ Description:
 */

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired  //引入DAO
    ProductCategoryRepository repository;


    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
