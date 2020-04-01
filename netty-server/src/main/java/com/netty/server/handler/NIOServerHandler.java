package com.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class NIOServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("server channelRead");
        ByteBuf buf = (ByteBuf) msg;
        byte[] reg = new byte[buf.readableBytes()];
        buf.readBytes(reg);
        String body = new String(reg, "UTF-8");
        log.info("服务端收到消息："+body);

        ByteBuf byteBuffer = Unpooled.buffer(1024);
        //byteBuffer.writeBytes(Charset.defaultCharset().encode("服务端发消息"));
        byteBuffer.writeBytes(Charset.defaultCharset().encode("liaotao"));
        ctx.write(byteBuffer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("server channelReadComplete");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("server exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}
