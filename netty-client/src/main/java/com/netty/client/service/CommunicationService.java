package com.netty.client.service;

import io.netty.channel.Channel;

public interface CommunicationService {
    Channel getChannel();
}
