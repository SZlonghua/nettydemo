package com.netty.server.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class R<T> implements Serializable {

    @ApiModelProperty("调用的结构消息")
    protected String message;

    @ApiModelProperty("成功响应的数据")
    protected T result;

    @ApiModelProperty(value = "状态码", required = true)
    protected int status;

    @ApiModelProperty(value = "时间戳", required = true, dataType = "Long")
    protected Long timestamp;

    @ApiModelProperty(value = "业务代码")
    protected String code;


    public static <T> R<T> error(int status, String message) {
        RBuilder<T> builder = builder();
        return builder
                .message(message)
                .status(status)
                .timestamp(currentTimeMillis())
                .build();
    }

    public static <T> R<T> error(String message) {
        return error(500, message);
    }

    public static <T> R<T> error(String code, String message) {
        RBuilder<T> builder = builder();
        return builder
                .message(message)
                .status(500)
                .code(code)
                .timestamp(currentTimeMillis())
                .build();
    }

    public static <T> R<T> ok(T result) {
        RBuilder<T> builder = builder();
        return builder
                .result(result)
                .timestamp(currentTimeMillis())
                .status(200)
                .build();
    }

    public static <T> R<T> ok() {
        return ok(null);
    }

    private static Long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
