package com.hotpot_ordering.controller;

import com.hotpot_ordering.VO.ProductInfoVO;
import com.hotpot_ordering.VO.ProductVO;
import com.hotpot_ordering.VO.ResultVO;
import com.hotpot_ordering.dataObject.ProductCategory;
import com.hotpot_ordering.dataObject.ProductInfo;
import com.hotpot_ordering.service.CategoryService;
import com.hotpot_ordering.service.ProductService;
import com.hotpot_ordering.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 10:58
 * @ Description:
 */

/*因为返回的是JSON格式，所以用 RestController */
@RestController
/*配置url前缀*/
@RequestMapping("buyer/product")
public class BuyerProduController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){

        /*  从数据库查询并且展示 */
       /* 1.查询所有上架商品*/
        List<ProductInfo> productInfoList = productService.findUpAll();

       /* 2.查询类目（一次性查询），不能用for循环依次去查询每个商品的类目
       *    所以为了性能的提升一定要一次性查询   */


        //方式一：
       /* List<Integer> categoryTypeList = new ArrayList<>();
          for(ProductInfo productInfo : productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }*/


        //方式二；利用java8的lambda表达式
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList =
        categoryService.findByCategoryTypeIn(categoryTypeList);

       /* 3.数据拼接,拼接后是下面的格式
        {                                                 //ProductVO
            "name": "青菜",
            "type": 5,
            "foods": [                                      //ProductInfoVO
                {
                    "id": "123456",
                    "name": "菠菜",
                    "price": 7,
                    "description": "新鲜的青菜",
                    "icon": "http://xxx.com",
                }
            ]
        }
        */


       List<ProductVO> productVOList = new ArrayList<>();
       /*先遍历类目，因为类目下面又有商品详情的List*/
        for (ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());



            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            /*遍历商品详情*/
            for(ProductInfo productInfo:productInfoList){
                /*根据类目判断一下是否相等*/
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    /*如果写set方法就要写五个，代码比较繁琐, Spring提供了BeanUtils
                    * 把一个对象(productInfo)里属性的值copy到另一个对象(productInfoVO)里*/
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

/*
        //这样new然后set的写法有点麻烦，可以尝试封装
        ResultVO resultVO = new ResultVO();
        resultVO.setData(productVOList);
        resultVO.setCode(0);
        resultVO.setMsg("成功");*/

        return ResultVOUtil.success(productVOList);
    }
}
