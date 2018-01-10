package com.hotpot_ordering.repository;

import com.hotpot_ordering.dataObject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 18:36
 * @ Description:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
