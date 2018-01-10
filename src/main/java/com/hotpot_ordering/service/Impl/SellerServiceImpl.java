package com.hotpot_ordering.service.Impl;

import com.hotpot_ordering.dataObject.SellerInfo;
import com.hotpot_ordering.repository.SellerInfoRepository;
import com.hotpot_ordering.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/5 21:45
 * @ Description:
 */
@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
