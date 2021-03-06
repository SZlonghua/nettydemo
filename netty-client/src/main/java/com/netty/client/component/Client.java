package com.netty.client.component;


import com.netty.client.handler.TerminaClientHandler;
import com.netty.common.codec.TerminaEncoder;
import com.netty.common.codec.TerminalDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Component
@ConfigurationProperties(prefix = "netty.client")
@Slf4j
public class Client {

    @Value("${netty.client.port}")
    private int port;


    private Channel clientChannel;
    private EventLoopGroup group;


    @PostConstruct
    public void init() {
        Bootstrap b = new Bootstrap();
        group = new NioEventLoopGroup();
        try {
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new IdleStateHandler(0,4,0, TimeUnit.SECONDS));
                            p.addLast("decoder",new TerminalDecoder(Integer.MAX_VALUE,0,2,-2,0));
                            p.addLast("encoder",new TerminaEncoder());
                            p.addLast(new TerminaClientHandler());
                        }
                    });
            // Start the client.
            ChannelFuture f = b.connect("127.0.0.1", port).sync();
            clientChannel = f.channel();
            log.info("client transport started!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @PreDestroy
    public void shutdown() throws InterruptedException {
        log.info("Stopping client transport!");
        try {
            clientChannel.close().sync();
        } finally {
            group.shutdownGracefully();
        }
        log.info("client transport stopped!");
    }

    public Channel getClientChannel() {
        return clientChannel;
    }

    public void setClientChannel(Channel clientChannel) {
        this.clientChannel = clientChannel;
    }
}
