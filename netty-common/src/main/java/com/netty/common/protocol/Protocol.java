package com.netty.common.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Protocol {

    private short packetLen;

    private byte command;

    private Object content;


    public short getHeaderLen(){
        return 3;
    }

}
