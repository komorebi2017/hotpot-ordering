package com.hotpot_ordering.VO;

import lombok.Data;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/1 19:45
 * @ Description: http返回的最外层对象
 */
@Data
public class ResultVO<T> {
    /*错误码*/
    private Integer code;
    /*提示信息*/
    private String msg;
    /*返回的具体内容*/
    private T data;
}
