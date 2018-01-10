package com.hotpot_ordering.repository;

import com.hotpot_ordering.dataObject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/5 21:36
 * @ Description:
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String>{

    SellerInfo findByOpenid(String openid);
}
