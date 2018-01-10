package com.hotpot_ordering.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/5 21:34
 * @ Description:
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;




}
