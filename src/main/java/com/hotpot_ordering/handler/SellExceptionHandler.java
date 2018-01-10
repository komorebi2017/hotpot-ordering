package com.hotpot_ordering.handler;

import com.hotpot_ordering.VO.ResultVO;
import com.hotpot_ordering.config.ProjectUrlConfig;
import com.hotpot_ordering.exception.SellException;
import com.hotpot_ordering.exception.SellerAuthorizeException;
import com.hotpot_ordering.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/8 20:25
 * @ Description:
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    /*异常的统一处理*/
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

}
