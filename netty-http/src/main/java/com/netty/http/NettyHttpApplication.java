package com.netty.http;


import com.netty.http.server.HttpServer;

public class NettyHttpApplication {

    public static void main(String[] args) throws Exception {
        HttpServer server = new HttpServer(8096);// 8081为启动端口
        server.start();
    }

}
