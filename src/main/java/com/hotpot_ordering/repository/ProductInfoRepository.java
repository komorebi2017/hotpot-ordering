package com.hotpot_ordering.repository;

import com.hotpot_ordering.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 9:31
 * @ Description:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
