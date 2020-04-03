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


    public short getPacketLen(){
        byte[] bytes = ((String) content).getBytes(Charset.forName("UTF-8"));
        return (short)(getHeaderLen()+bytes.length);
    }

    private short getHeaderLen(){
        return 3;
    }

}
