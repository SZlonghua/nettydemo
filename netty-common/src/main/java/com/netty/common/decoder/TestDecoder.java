package com.netty.common.decoder;

import com.netty.common.constant.CommandConstant;
import com.netty.common.model.Test;

import java.nio.charset.Charset;

@com.netty.common.annotation.Decoder(CommandConstant.TEST)
public class TestDecoder implements Decoder {
    @Override
    public Object decode(byte[] content) throws Exception {
        return new Test(new String(content, Charset.defaultCharset()));
    }
}
