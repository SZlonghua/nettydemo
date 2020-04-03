package com.netty.common.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class BaseDecoder implements Decoder {
    @Override
    public Object decode(byte[] content) {
        ByteBuf byteBuffer = Unpooled.buffer(content.length);
        byteBuffer.writeBytes(content);
        return byteBuffer;
    }
}
