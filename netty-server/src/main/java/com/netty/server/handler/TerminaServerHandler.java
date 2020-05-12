package com.netty.server.handler;

import com.netty.common.constant.CommandMapping;
import com.netty.common.constant.HandleInfo;
import com.netty.common.model.Test;
import com.netty.common.protocol.Protocol;
import com.netty.server.util.ChannelAttrUtil;
import com.netty.server.util.ChannelHolder;
import com.netty.server.util.SpringContextUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Date;

@Slf4j
public class TerminaServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Protocol message = (Protocol) msg;
        log.info("服务端收到消息："+message);
        ChannelAttrUtil.putClientId(ctx.channel(),message.getClientId());
        ChannelHolder.put(message.getClientId(),ctx.channel());

        HandleInfo handleInfo = CommandMapping.getHandleName(message.getCommand());
        Class<?> clazz = Class.forName(handleInfo.getHandleClass());
        Object handleInstance = SpringContextUtil.getBean(clazz);
        Method method = clazz.getMethod(handleInfo.getHandleMethod(),Protocol.class);
        method.invoke(handleInstance,message);
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
            if (event.state()== IdleState.READER_IDLE){
                //log.info("已经5秒未收到客户端 {} 的消息了,服务端读超时 {}",ChannelAttrUtil.getClientId(ctx.channel()),new Date());
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("server exceptionCaught");

        String clientId = ChannelAttrUtil.getClientId(ctx.channel());
        ChannelHolder.remove(clientId);

        cause.printStackTrace();
        ctx.close();
    }
}
