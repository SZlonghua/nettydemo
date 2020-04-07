package com.netty.server.util;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelHolder {

    private static final Map<String, Channel> channelMapping = new ConcurrentHashMap<String, Channel>(16);

    public static void put(String id,Channel channel){
        channelMapping.put(id, channel);
    }

    public static Channel get(String id){
        return channelMapping.get(id);
    }
}
