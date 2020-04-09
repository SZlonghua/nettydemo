package com.netty.common.codec;

import com.netty.common.constant.CommandMapping;
import com.netty.common.encoder.Encoder;
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
        Encoder encoder = CommandMapping.getEncoder(msg.getCommand());
        out.writeBytes(encoder.encoder(msg));
    }
}
