package com.liaotao.client.encoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.mqtt.MqttMessage;

import java.util.List;

public class MyEncoder extends MessageToMessageEncoder<MqttMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MqttMessage msg, List<Object> out) throws Exception {
        throw new IllegalArgumentException("ssssssssssssssssssss");
    }
}
