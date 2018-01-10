package com.hotpot_ordering.dataObject.dao;

import com.hotpot_ordering.dataObject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/9 18:05
 * @ Description:
 */

public class ProductCategoryDao {

    @Autowired
    ProductCategoryMapper mapper;

    public int insertByMap(Map<String, Object> map){
        return mapper.insertByMap(map);
    }


}
