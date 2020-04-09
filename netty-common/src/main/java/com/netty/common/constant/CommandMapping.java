package com.netty.common.constant;

import com.netty.common.decoder.Decoder;
import com.netty.common.encoder.Encoder;
import java.util.HashMap;
import java.util.Map;

public class CommandMapping {

    private static Boolean initCompleted = false;

    private static final Map<Byte, String> decoderMapping = new HashMap<Byte, String>();
    private static final Map<Byte, String> encoderMapping = new HashMap<Byte, String>();
    private static final Map<Byte, String> messageMapping = new HashMap<Byte, String>();
    private static final Map<Byte, HandleInfo> handleMapping = new HashMap<Byte, HandleInfo>();

    public static void registryDecoder(Byte command,String className){
        decoderMapping.put(command,className);
    }
    public static void registryEncoder(Byte command,String className){
        encoderMapping.put(command,className);
    }
    public static void registryMessage(Byte command,String className){
        messageMapping.put(command,className);
    }
    public static void registryHandle(Byte command,HandleInfo handleInfo){
        handleMapping.put(command,handleInfo);
    }

    public static Decoder getDecoder(Byte command) throws Exception {
        String className = decoderMapping.get(command);
        Class<?> aClass = Class.forName(className);
        return (Decoder) aClass.newInstance();
    }
    public static Encoder getEncoder(Byte command) throws Exception {
        String className = encoderMapping.get(command);
        Class<?> aClass = Class.forName(className);
        return (Encoder) aClass.newInstance();
    }
    public static Object getMessage(Byte command) throws Exception {
        String className = messageMapping.get(command);
        Class<?> aClass = Class.forName(className);
        return aClass.newInstance();
    }
    public static HandleInfo getHandleName(Byte command) throws Exception {
        return handleMapping.get(command);
    }

    public static synchronized Boolean getInitCompleted() {
        return initCompleted;
    }

    public static synchronized void setInitCompleted(Boolean initCompleted) {
        CommandMapping.initCompleted = initCompleted;
    }
}
