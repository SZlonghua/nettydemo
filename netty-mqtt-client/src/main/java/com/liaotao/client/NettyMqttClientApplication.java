package com.liaotao.client;

import com.liaotao.client.client.MqttClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class NettyMqttClientApplication {

    public static void main(String[] args) throws Exception {
        new MqttClient(8100).start();
    }

}
