package com.netty.server.service.Impl;

import com.netty.common.annotation.Handler;
import com.netty.common.constant.CommandConstant;
import com.netty.common.model.Test;
import com.netty.common.protocol.Protocol;
import com.netty.server.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends CommunicationServiceImpl implements TestService {
    @Override
    @Handler(CommandConstant.TEST)
    public void test(Protocol message) {
        Protocol send = new Protocol();
        byte command = 96;
        send.setCommand(command);
        send.setClientId("13670226316");
        Test test = new Test("我收到了消息333333");
        send.setContent(test);
        sendMessage(send);
    }
}
