package com.zqz.mall.handler;

import com.zqz.mall.common.bean.R;
import com.zqz.mall.enums.ResultEnum;
import com.zqz.mall.exception.MallException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;


/**
 * @Author: ZQZ
 * @Description: 自定义异常
 * @ClassName: MallExceptionHandler
 * @Date: Created in 18:10 2023-6-13
 */
@RestControllerAdvice
public class MallExceptionHandler {


    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        R result = new R();
        result.setResultCode(500);
        //区分是否为自定义异常
        if (e instanceof MallException) {
            result.setMessage(e.getMessage());
            if (e.getMessage().equals(ResultEnum.NOT_LOGIN_ERROR.getResult()) || e.getMessage().equals(ResultEnum.TOKEN_EXPIRE_ERROR.getResult())) {
                result.setResultCode(416);
            } else if (e.getMessage().equals(ResultEnum.ADMIN_NOT_LOGIN_ERROR.getResult()) || e.getMessage().equals(ResultEnum.ADMIN_TOKEN_EXPIRE_ERROR.getResult())) {
                result.setResultCode(419);
            }
        } else {
            e.printStackTrace();
            result.setMessage("未知异常，请查看控制台日志！");
        }
        return result;
    }


    @ExceptionHandler(BindException.class)
    public Object bindException(BindException e) {
        R result = new R();
        result.setResultCode(510);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object bindException(MethodArgumentNotValidException e) {
        R result = new R();
        result.setResultCode(510);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }
}
