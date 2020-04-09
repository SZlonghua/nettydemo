package com.netty.common.encoder;

import com.netty.common.protocol.Protocol;

public interface Encoder {

    byte[] encoder(Protocol protocol) throws Exception;
}
