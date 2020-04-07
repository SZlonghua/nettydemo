package com.netty.client.handler;

import com.netty.common.model.Test;
import com.netty.common.protocol.Protocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TerminaClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client channelActive");
        Protocol send = new Protocol();
        byte command = 96;
        send.setCommand(command);
        send.setClientId("13670226316");
        Test test = new Test("客户端发送的消息11111111111111111");
        send.setContent(test);
        ctx.writeAndFlush(send);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Protocol message = (Protocol) msg;
        log.info("客户端收到消息："+message);
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
