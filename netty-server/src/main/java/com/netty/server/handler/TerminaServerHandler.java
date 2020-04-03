package com.netty.server.handler;

import com.netty.common.protocol.Protocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TerminaServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Protocol message = (Protocol) msg;
        log.info("服务端收到消息："+message);
        Protocol send = new Protocol();
        byte command = 96;
        send.setCommand(command);
        send.setContent("我收到了消息333333");
        ctx.write(send);
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
