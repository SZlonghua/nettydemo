package com.liaotao.nettymqtt;

import com.liaotao.nettymqtt.server.MqttServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class NettyMqttApplication {

    public static void main(String[] args) throws Exception {
        new MqttServer(8100).start();
    }

}
