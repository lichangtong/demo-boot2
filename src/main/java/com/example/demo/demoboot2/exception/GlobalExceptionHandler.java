package com.example.demo.demoboot2.exception;

import com.example.demo.demoboot2.Result.ApiResult;
import com.google.inject.internal.util.Maps;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-13 14:57
 * @Description:
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResult defaultErrorHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode("400");
        org.springframework.validation.FieldError error = e.getBindingResult().getFieldErrors().get(0);
        apiResult.setMsg(error.getDefaultMessage());
        return apiResult;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        ApiResult apiResult = new ApiResult();
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            apiResult.setCode("404");
            apiResult.setMsg(req.getRequestURI() + "接口不存在");
        } else {
            apiResult.setCode("400");
            apiResult.setMsg(e.getMessage());
        }
        String message = ExceptionUtils.getStackTrace(e);
        message = ExceptionUtils.getMessage(e);
        message = ExceptionUtils.getRootCauseMessage(e);
        apiResult.setMsg(message);
        apiResult.setData(Maps.newHashMap());
        return apiResult;
    }

}
