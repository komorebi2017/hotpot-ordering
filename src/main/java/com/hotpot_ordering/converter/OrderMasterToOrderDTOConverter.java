package com.hotpot_ordering.converter;

import com.hotpot_ordering.dataObject.OrderMaster;
import com.hotpot_ordering.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 21:08
 * @ Description:
 */
public class OrderMasterToOrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e ->
                convert(e)).collect(Collectors.toList());
    }
}
