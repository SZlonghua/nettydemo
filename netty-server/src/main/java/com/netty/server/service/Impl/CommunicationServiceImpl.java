package com.netty.server.service.Impl;

import com.netty.common.protocol.Protocol;
import com.netty.server.exception.NonChannelException;
import com.netty.server.service.CommunicationService;
import com.netty.server.util.ChannelHolder;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

@Service
public class CommunicationServiceImpl implements CommunicationService {
    @Override
    public void sendMessage(Protocol message) {
        Channel channel = ChannelHolder.get(message.getClientId());
        if(channel==null){
            throw new NonChannelException("客户端没有连接");
        }
        channel.writeAndFlush(message);
    }
}
