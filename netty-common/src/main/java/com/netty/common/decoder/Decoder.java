package com.netty.common.decoder;

import java.io.UnsupportedEncodingException;

public interface Decoder {

    Object decode(byte[] content) throws Exception;
}
