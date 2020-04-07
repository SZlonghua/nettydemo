package com.netty.common.encoder;

import com.netty.common.protocol.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public abstract class BaseEncoder implements Encoder {
    @Override
    public byte[] encoder(Protocol protocol) throws Exception {
        //获取content字节码
        byte[] bytes = getContentBytes(protocol);
        //获取总长度
        short headerLen = protocol.getHeaderLen();
        short packetLen = (short)(headerLen + bytes.length);
        //写入
        ByteBuf byteBuffer = Unpooled.buffer(packetLen);
        byteBuffer.writeShort(packetLen);
        byteBuffer.writeByte(protocol.getCommand());
        byteBuffer.writeBytes(protocol.getClientId().getBytes(Charset.defaultCharset()));
        byteBuffer.writeBytes(bytes);
        //转化为字节码
        /*byte[] b = new byte[packetLen];
        byteBuffer.readBytes(b);
        return b;*/
        return byteBuffer.array();
    }

    abstract  byte[] getContentBytes(Protocol protocol);
}
