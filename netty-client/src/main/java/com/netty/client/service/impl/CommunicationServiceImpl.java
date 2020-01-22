package com.netty.client.service.impl;

import com.netty.client.component.Client;
import com.netty.client.service.CommunicationService;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;

public class CommunicationServiceImpl implements CommunicationService {
    @Autowired
    Client client;

    public Channel getChannel() {
        return client.getClientChannel();
    }

}
