package com.netty.server.service;

import com.netty.common.protocol.Protocol;

public interface CommunicationService {

    void sendMessage(Protocol message);
}
