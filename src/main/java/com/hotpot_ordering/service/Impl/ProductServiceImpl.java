package com.hotpot_ordering.service.Impl;

import com.hotpot_ordering.dataObject.ProductInfo;
import com.hotpot_ordering.dto.CartDTO;
import com.hotpot_ordering.enums.ProductStatusEnum;
import com.hotpot_ordering.enums.ResultEnum;
import com.hotpot_ordering.exception.SellException;
import com.hotpot_ordering.repository.ProductInfoRepository;
import com.hotpot_ordering.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//import java.awt.print.Pageable;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 9:59
 * @ Description:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    /**
     *@ Author:陌北有棵树
     *@ Date: 2018/1/1 10:39
     *@ Description: 关于这里的分页，传入的参数如果是Pageable类型会报错
     *               所以改成了PageRequest，但不知道为什么视频里面没报错
     *               发现原因：（1）org.springframework.data.domain.Pageable
     *                       （2）java.awt.print.Pageable
     *                       第一次写的时候引入的是第二个，但应该是第一个，以后了解了原理记得补上
     *               于是参数类型改回了 Pageable
     *@ Param: Pageable pageable
     *@ Return:Page<~>
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional  //要加事务
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo =repository.findOne(cartDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);


        }
    }


    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        if(productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return  repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        if(productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum() == ProductStatusEnum.DOWM){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWM.getCode());
        return  repository.save(productInfo);
    }
}
