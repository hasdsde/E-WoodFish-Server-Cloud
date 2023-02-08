package com.hasd.config;

import com.hasd.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 16:15
 **/

@ControllerAdvice
public class ExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(NormalException.class)
    public Result handleExceptionHandler(NormalException e) {
        return Result.success(e.getCode(), "异常:" + e.getMsg(), null);
    }
}
