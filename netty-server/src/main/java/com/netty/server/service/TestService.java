package com.netty.server.service;

import com.netty.common.protocol.Protocol;

public interface TestService extends CommunicationService {

    void test(Protocol message);
}
