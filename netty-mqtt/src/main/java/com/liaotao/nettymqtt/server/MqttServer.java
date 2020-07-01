package com.liaotao.nettymqtt.server;

import com.liaotao.nettymqtt.handler.EHandler;
import com.liaotao.nettymqtt.handler.MqttHeartBeatBrokerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class MqttServer {
    int port;

    public MqttServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss, work)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("encoder", MqttEncoder.INSTANCE);
                        ch.pipeline().addLast("decoder", new MqttDecoder());
                        ch.pipeline().addLast("heartBeatHandler", new IdleStateHandler(45, 0, 0, TimeUnit.SECONDS));
                        ch.pipeline().addLast("handler", MqttHeartBeatBrokerHandler.INSTANCE);
                        //ch.pipeline().addLast("handler2", EHandler.INSTANCE);

                    }
                });
        ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
        System.out.println(" server start up on port : " + port);
        f.channel().closeFuture().sync();

    }
}
