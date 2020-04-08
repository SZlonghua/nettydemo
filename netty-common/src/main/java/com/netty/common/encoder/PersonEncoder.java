package com.netty.common.encoder;

import com.alibaba.fastjson.JSON;
import com.netty.common.constant.CommandConstant;
import com.netty.common.model.Person;
import com.netty.common.protocol.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

@com.netty.common.annotation.Encoder(CommandConstant.PERSON)
public class PersonEncoder extends BaseEncoder implements Encoder {
    @Override
    byte[] getContentBytes(Protocol protocol) {
        Person content = (Person)protocol.getContent();
        String s = JSON.toJSONString(content);
        return s.getBytes(Charset.defaultCharset());
    }
}
