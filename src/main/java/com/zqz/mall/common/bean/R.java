package com.zqz.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description: 统一返回响应
 * @ClassName: R
 * @Date: Created in 14:42 2023-6-13
 */
@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = 567769524864106632L;

    private static final String SUCCESS_MESSAGE = "SUCCESS";
    private static final String FAIL_MESSAGE = "FAIL";
    private static final int SUCCESS = 200;
    private static final int ERROR = 500;

    private Integer resultCode;

    private String message;

    private T data;

    public R(){}


    public R(Integer resultCode, String message){
        this.resultCode = resultCode;
        this.message = message;
    }


    public static R success(){
        R r = new R<>();
        r.setResultCode(SUCCESS);
        r.setMessage(SUCCESS_MESSAGE);
        return r;
    }

    public static R success(String message){
        R r = new R<>();
        r.setResultCode(SUCCESS);
        r.setMessage(message);
        return r;
    }

    public static R success(Object data){
        R r = new R<>();
        r.setResultCode(SUCCESS);
        r.setMessage(SUCCESS_MESSAGE);
        r.setData(data);
        return r;
    }

    public static R fail(Integer resultCode, String message){
        R r = new R<>();
        r.setResultCode(resultCode);
        r.setMessage(message);
        return r;
    }

    public static R fail(String message){
        R r = new R<>();
        r.setResultCode(ERROR);
        r.setMessage(message);
        return r;
    }



}
