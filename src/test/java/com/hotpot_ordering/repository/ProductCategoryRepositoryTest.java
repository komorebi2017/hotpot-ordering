package com.hotpot_ordering.repository;

import com.hotpot_ordering.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 8:04
 * @ Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    @Transactional  //测试时加这个事务就完全回滚了，测试时对数据库的操作不保存
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("海鲜",4);

        /*更新还是用save方法，不过需要设置主键*/
        /*productCategory.setCategoryId(2);
        productCategory.setCategoryName("牛肉");
        productCategory.setCategoryType(10);*/
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByCategoryTypeInTest(){

        List<Integer> list = Arrays.asList(1,2,3);

        /*进行这种查询需要有一个无参构造方法*/
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }

}