package com.hotpot_ordering.service;

import com.hotpot_ordering.dataObject.SellerInfo;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/5 21:44
 * @ Description:
 */
public interface SellerService {

    /**
     *@ Author:陌北有棵树
     *@ Date: 2018/1/5 21:44
     *@ Description: 通过openid查询
     *@ Param:
     *@ Return:
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
