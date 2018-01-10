package com.hotpot_ordering.repository;

import com.hotpot_ordering.dataObject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 9:33
 * @ Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("54321");
        productInfo.setProductName("茼蒿");
        productInfo.setProductPrice(new BigDecimal(8));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("新鲜的青菜");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(5);

        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> productInfoList =repository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }

}