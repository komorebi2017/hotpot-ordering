package com.hotpot_ordering.controller;

import com.hotpot_ordering.dataObject.ProductCategory;
import com.hotpot_ordering.dataObject.ProductInfo;
import com.hotpot_ordering.dto.OrderDTO;
import com.hotpot_ordering.form.ProductForm;
import com.hotpot_ordering.service.CategoryService;
import com.hotpot_ordering.service.OrderService;
import com.hotpot_ordering.service.ProductService;
import com.hotpot_ordering.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/4 19:27
 * @ Description:
 */

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list", map);
    }


    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map){
        try {
            productService.onSale(productId);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);

    }


    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map){
        try {
            productService.offSale(productId);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);

    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                      Map<String, Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo =productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        //查询所有的类目
        List<ProductCategory> categoryList= categoryService.findAll();
        map.put("categoryList",categoryList);

        return new ModelAndView("product/index",map);
    }

    /**
     *@ Author:陌北有棵树
     *@ Date: 2018/1/5 20:23
     *@ Description: 保存，更新
     *@ Param:
     *@ Return:
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            /*如果是新增的话，productId是空的，所以做个判断*/
            if(!StringUtils.isEmpty(form.getProductId())){
                productInfo =productService.findOne(form.getProductId());
            }else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (Exception e) {
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);

    }




}
