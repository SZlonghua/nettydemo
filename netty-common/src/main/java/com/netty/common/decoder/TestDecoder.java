package com.netty.common.decoder;

import com.netty.common.constant.CommandConstant;
import com.netty.common.model.Test;

@com.netty.common.annotation.Decoder(CommandConstant.TEST)
public class TestDecoder implements Decoder {
    @Override
    public Object decode(byte[] content) throws Exception {
        return new Test(new String(content,"UTF-8"));
    }
}
