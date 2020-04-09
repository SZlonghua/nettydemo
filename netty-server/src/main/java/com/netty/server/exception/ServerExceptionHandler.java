package com.netty.server.exception;

import com.netty.server.model.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServerExceptionHandler {

    @ExceptionHandler(NonChannelException.class)
    public R<String> handleNonChannelException(NonChannelException e){
        return R.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<String> handleException(Exception e){
        return R.error("请联系管理员");
    }
}
