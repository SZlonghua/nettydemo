package com.netty.client.handler;

import com.netty.common.annotation.IOTListenerAnnotationRegistry;
import com.netty.common.constant.CommandConstant;
import com.netty.common.constant.CommandMapping;
import com.netty.common.model.Person;
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
        while (!CommandMapping.getInitCompleted()){
            IOTListenerAnnotationRegistry.init();
        }
        Protocol send = new Protocol();
        byte command = CommandConstant.PERSON;
        send.setCommand(command);
        send.setClientId("13670226316");
        Person test = new Person("1","liaotao");
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
