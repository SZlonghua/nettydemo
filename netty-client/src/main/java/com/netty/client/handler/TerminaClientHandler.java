package com.netty.client.handler;

import com.netty.common.annotation.IOTListenerAnnotationRegistry;
import com.netty.common.constant.CommandConstant;
import com.netty.common.constant.CommandMapping;
import com.netty.common.model.Person;
import com.netty.common.model.Test;
import com.netty.common.protocol.Protocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;


@Slf4j
public class TerminaClientHandler extends ChannelInboundHandlerAdapter {

    private int count;

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

        log.info("channel handle name {} count {}",ctx.name(),count++);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("channelRead method channel handle name {} count {}",ctx.name(),count++);


        Protocol message = (Protocol) msg;
        log.info("客户端收到消息："+message);
        Protocol send = new Protocol();
        byte command = CommandConstant.PERSON;
        send.setCommand(command);
        send.setClientId("13670226316");
        Person test = new Person("3","liaotao");
        send.setContent(test);
        ctx.writeAndFlush(send);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("server channelReadComplete");
        ctx.flush();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state()== IdleState.WRITER_IDLE){
                //log.info("已经4秒未向服务端写消息了,客户端写超时 {}",new Date());
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("server exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}
