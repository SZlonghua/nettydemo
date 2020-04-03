package com.netty.common.constant;

import com.netty.common.decoder.Decoder;
import com.netty.common.decoder.TestDecoder;
import com.netty.common.encoder.Encoder;
import com.netty.common.encoder.TestEncoder;
import com.netty.common.model.Test;
import java.util.HashMap;
import java.util.Map;

public class CommandMapping {

    private static final Map<Byte, Config> decoderMapping = new HashMap<Byte, Config>();

    public static final Byte TEST = 0x60;

    static {
        decoderMapping.put(TEST, new Config(Test.class, TestDecoder.class, TestEncoder.class));
    }


    public static Config getConfig(Byte command) throws Exception {
        Config config = decoderMapping.get(command);
        if (config == null) {
            config = decoderMapping.get(TEST);
        }
        return config;
    }

    public static Decoder getDecoder(Byte command) throws Exception {
        Config config = getConfig(command);
        Class decoderType = config.getDecoderType();
        return (Decoder) decoderType.newInstance();
    }
    public static Encoder getEncoder(Byte command) throws Exception {
        Config config = getConfig(command);
        Class decoderType = config.getEncoderType();
        return (Encoder) decoderType.newInstance();
    }
}
