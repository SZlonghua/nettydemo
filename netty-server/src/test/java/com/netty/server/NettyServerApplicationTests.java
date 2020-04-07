package com.netty.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;

//@SpringBootTest
class NettyServerApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("13670226316".getBytes(Charset.defaultCharset()).length);
    }

}
