package com.netty.server.util;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

public class ChannelAttrUtil {

    public static final AttributeKey<String> CLIENT_ID = AttributeKey.valueOf("client_id");

    public static void putClientId(Channel channel, String clientId) {
        channel.attr(CLIENT_ID).set(clientId);
    }

    public static String getClientId(Channel channel) {
        return (String)channel.attr(CLIENT_ID).get();
    }
}
