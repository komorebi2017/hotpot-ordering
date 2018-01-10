package com.hotpot_ordering.dataObject.mapper;

import com.hotpot_ordering.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/9 15:14
 * @ Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("category_name","主食");
        map.put("category_type",6);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }


    @Test
    public void insertByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(15);
        productCategory.setCategoryName("小菜");
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);

    }

    @Test
    public void findByCategoryType(){
        ProductCategory result = mapper.findByCategoryType(6);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateByCategoryType(){
        int result = mapper.updateByCategoryType("凉菜",15);
        Assert.assertEquals(1,result);

    }

    @Test
    public void deleteByCategoryType(){
        int result = mapper.deleteByCategoryType(6);
        Assert.assertEquals(1,result);

    }
}