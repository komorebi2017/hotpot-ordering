package com.hotpot_ordering.repository;

import com.hotpot_ordering.dataObject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 18:38
 * @ Description:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);
}
