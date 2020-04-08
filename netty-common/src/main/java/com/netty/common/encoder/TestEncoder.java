package com.netty.common.encoder;

import com.netty.common.constant.CommandConstant;
import com.netty.common.model.Test;
import com.netty.common.protocol.Protocol;
import java.nio.charset.Charset;

@com.netty.common.annotation.Encoder(CommandConstant.TEST)
public class TestEncoder extends BaseEncoder implements Encoder {

    @Override
    byte[] getContentBytes(Protocol protocol) {
        Test content = (Test)protocol.getContent();
        return content.getContent().getBytes(Charset.defaultCharset());
    }
}
