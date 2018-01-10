package com.hotpot_ordering.controller;

import com.hotpot_ordering.dto.OrderDTO;
import com.hotpot_ordering.enums.ResultEnum;
import com.hotpot_ordering.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/3 19:08
 * @ Description:
 */
@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     *@ Author:陌北有棵树
     *@ Date: 2018/1/3 19:11
     *@ Description: 订单列表
     *@ Param: page 第几页，从第一页开始
     *         size 一页有多少条数据
     *@ Return:
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list", map);
    }


    @RequestMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (Exception e) {
            log.error("卖家端取消订单：发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");
    }


    /**
     *@ Author:陌北有棵树
     *@ Date: 2018/1/4 18:28
     *@ Description: 订单详情
     *@ Param:
     *@ Return:
     */
    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
             orderDTO = orderService.findOne(orderId);
        } catch (Exception e) {
            log.error("卖家端查询订单：发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    /**
     *@ Author:陌北有棵树
     *@ Date: 2018/1/4 18:51
     *@ Description: 完结订单
     *@ Param:
     *@ Return:
     */
    @RequestMapping("/finish")
    public ModelAndView fished(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (Exception e) {
            log.error("卖家端完结订单：发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");

    }


}
