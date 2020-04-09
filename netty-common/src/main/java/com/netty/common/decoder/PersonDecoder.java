package com.netty.common.decoder;

import com.alibaba.fastjson.JSON;
import com.netty.common.constant.CommandConstant;
import com.netty.common.model.Person;

import java.nio.charset.Charset;

@com.netty.common.annotation.Decoder(CommandConstant.PERSON)
public class PersonDecoder implements Decoder {
    @Override
    public Object decode(byte[] content) throws Exception {
        String s = new String(content, Charset.defaultCharset());
        return JSON.parseObject(s,Person.class);
    }
}
