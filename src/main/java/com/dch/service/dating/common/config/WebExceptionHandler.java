package com.dch.service.dating.common.config;

import com.dch.service.dating.common.ApiResult;
import com.dch.service.dating.common.exception.BizExcpetion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {

    @ExceptionHandler
    public ApiResult bizException(BizExcpetion e) {
        log.error("WebExceptionHandler bizException ", e);
        return ApiResult.error(e.getCode(), e.getMsg());
    }
}
