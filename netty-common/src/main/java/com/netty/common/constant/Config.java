package com.netty.common.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class Config {

    private Class contentType;
    private Class decoderType;
    private Class encoderType;

    public Config(Class contentType, Class decoderType, Class encoderType) {
        this.contentType = contentType;
        this.decoderType = decoderType;
        this.encoderType = encoderType;
    }
}
