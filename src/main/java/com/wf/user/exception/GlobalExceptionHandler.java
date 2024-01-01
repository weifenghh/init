package com.wf.user.exception;

import com.wf.user.common.BaseResponse;
import com.wf.user.common.ErrorCode;
import com.wf.user.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/30 12:07
 * @Package com.wf.user.exception
 * @Version 1.0
 * @Since 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e){
        log.error("BusinessException错误信息:",e);
        return ResultUtils.error(e.getMessage(),e.getCode());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> businessExceptionHandler(RuntimeException e){
        log.error("RuntimeException错误:" + e);
        return ResultUtils.error(e.getMessage(),ErrorCode.SYSTEM_ERROR.getCode());
    }

}
