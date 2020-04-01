package com.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;


@Slf4j
public class NIOClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client channelActive");
        ByteBuf byteBuffer = Unpooled.buffer(1024);
        byteBuffer.writeBytes(Charset.defaultCharset().encode("客户端发消息"));
        ctx.writeAndFlush(byteBuffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("client channelRead");
        ByteBuf buf = (ByteBuf) msg;
        byte[] reg = new byte[buf.readableBytes()];
        buf.readBytes(reg);
        String body = new String(reg, "UTF-8");
        log.info("客户端收到消息："+body);

        ByteBuf byteBuffer = Unpooled.buffer(1024);
        byteBuffer.writeBytes(Charset.defaultCharset().encode("客户端发消息"));
        ctx.writeAndFlush(byteBuffer);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("client channelReadComplete");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("client exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}
