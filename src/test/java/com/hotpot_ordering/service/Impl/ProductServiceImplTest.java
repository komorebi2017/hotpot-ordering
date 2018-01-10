package com.hotpot_ordering.service.Impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.hotpot_ordering.dataObject.ProductInfo;
import com.hotpot_ordering.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 10:22
 * @ Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest

public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;
    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("54321");
        Assert.assertEquals("54321",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
       List<ProductInfo> productInfoList = productService.findUpAll();
       Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage =productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("63225");
        productInfo.setProductName("生菜");
        productInfo.setProductPrice(new BigDecimal(7));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("新鲜的青菜");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(5);

        ProductInfo result =productService.save(productInfo);
        Assert.assertNotNull(result);


    }


    @Test
    public void onSale(){
        ProductInfo result = productService.onSale("23458");
        Assert.assertEquals(ProductStatusEnum.UP,result.getProductStatusEnum());
    }

    @Test
    public void offSale(){
        ProductInfo result = productService.offSale("23458");
        Assert.assertEquals(ProductStatusEnum.DOWM,result.getProductStatusEnum());
    }


}