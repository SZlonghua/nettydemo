package com.netty.common.codec;

import com.netty.common.protocol.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

public class TerminaEncoder extends MessageToByteEncoder<Protocol> {


    protected void encode(ChannelHandlerContext ctx, Protocol msg, ByteBuf out) throws Exception {
        if(msg == null){
            throw new Exception("msg is null");
        }
        out.writeShort(msg.getPacketLen());
        out.writeByte(msg.getCommand());
        String content = (String)msg.getContent();
        out.writeBytes(content.getBytes(Charset.forName("UTF-8")));
    }
}
