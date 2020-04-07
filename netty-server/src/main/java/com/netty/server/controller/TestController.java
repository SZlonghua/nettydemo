package com.netty.server.controller;

import com.netty.common.model.Test;
import com.netty.common.protocol.Protocol;
import com.netty.server.util.ChannelHolder;
import io.netty.channel.Channel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    public String test(){
        Channel channel = ChannelHolder.get("13670226316");

        Protocol send = new Protocol();
        byte command = 96;
        send.setCommand(command);
        send.setClientId("13670226316");
        Test test = new Test("ceshi hello world!");
        send.setContent(test);

        channel.writeAndFlush(send);
        return "ok";
    }
}
