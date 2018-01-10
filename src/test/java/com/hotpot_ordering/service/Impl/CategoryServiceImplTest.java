package com.hotpot_ordering.service.Impl;

import com.hotpot_ordering.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 9:05
 * @ Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest

public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        /*查到的数量不为0，就通过*/
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3));
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("菌类",4);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }

}