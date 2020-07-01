package com.netty.http.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class PrintHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        //读取content
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        log.debug("http 传来消息: {}",new String(bytes, Charset.defaultCharset()));
        System.out.println(new String(bytes, Charset.defaultCharset()));

    }
}
