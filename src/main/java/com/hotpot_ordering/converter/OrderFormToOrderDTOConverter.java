package com.hotpot_ordering.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hotpot_ordering.dataObject.OrderDetail;
import com.hotpot_ordering.dto.OrderDTO;
import com.hotpot_ordering.enums.ResultEnum;
import com.hotpot_ordering.exception.SellException;
import com.hotpot_ordering.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 22:15
 * @ Description:
 */
@Slf4j
public class OrderFormToOrderDTOConverter {

    /*因为名字是不一样的，所以不能用BeanUtils的方法*/

    public static OrderDTO convert(OrderForm orderForm){

        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());


        List<OrderDetail> orderDetailList = new ArrayList<>();
        /*用Gson，可以把Json转成List*/
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());

        }catch (Exception e){
            log.error("对象转换错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;

    }
}
