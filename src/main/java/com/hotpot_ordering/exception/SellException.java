package com.hotpot_ordering.exception;

import com.hotpot_ordering.enums.ResultEnum;
import lombok.Data;
import lombok.Getter;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/2 19:32
 * @ Description:
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }


    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
