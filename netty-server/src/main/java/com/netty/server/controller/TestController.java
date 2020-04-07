package com.netty.server.controller;

import com.netty.common.model.Test;
import com.netty.common.protocol.Protocol;
import com.netty.server.model.R;
import com.netty.server.service.TestService;
import com.netty.server.util.ChannelHolder;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public R test(){

        Protocol message = new Protocol();
        byte command = 96;
        message.setCommand(command);
        message.setClientId("13670226316");
        Test test = new Test("ceshi hello world!");
        message.setContent(test);

        testService.sendMessage(message);
        return R.ok();
    }
}
