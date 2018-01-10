package com.hotpot_ordering.service;

import com.hotpot_ordering.dataObject.ProductInfo;
import com.hotpot_ordering.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 9:50
 * @ Description:
 */
public interface ProductService {

    ProductInfo findOne(String productId);
    
    /* 查询在架的商品列表*/
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /*加库存*/
    void increaseStock(List<CartDTO> cartDTOList);

    /*减库存*/
    void decreaseStock(List<CartDTO> cartDTOList);


    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);



   
}
