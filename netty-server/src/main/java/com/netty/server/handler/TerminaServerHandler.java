package com.netty.server.handler;

import com.netty.common.model.Test;
import com.netty.common.protocol.Protocol;
import com.netty.server.util.ChannelAttrUtil;
import com.netty.server.util.ChannelHolder;
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
        send.setClientId("13670226316");
        Test test = new Test("我收到了消息333333");
        send.setContent(test);

        ChannelAttrUtil.putClientId(ctx.channel(),message.getClientId());
        ChannelHolder.put(message.getClientId(),ctx.channel());

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

        String clientId = ChannelAttrUtil.getClientId(ctx.channel());
        ChannelHolder.remove(clientId);

        cause.printStackTrace();
        ctx.close();
    }
}
