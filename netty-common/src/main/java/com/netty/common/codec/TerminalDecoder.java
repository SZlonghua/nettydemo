package com.netty.common.codec;

import com.netty.common.constant.CommandMapping;
import com.netty.common.decoder.Decoder;
import com.netty.common.protocol.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class TerminalDecoder extends LengthFieldBasedFrameDecoder {

    private static final int HEADER_SIZE = 3;

    public TerminalDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //在这里调用父类的方法,实现指得到想要的部分,我在这里全部都要,也可以只要body部分
        in = (ByteBuf) super.decode(ctx,in);
        if(in == null){
            return null;
        }
        if(in.readableBytes()<HEADER_SIZE){
            throw new Exception("字节数不足");
        }
        int readableBytes = in.readableBytes();
        //读取length字段
        short packetLen = in.readShort();
        //读取command字段
        byte command = in.readByte();

        if(readableBytes!=packetLen){
            throw new Exception("标记的长度不符合实际长度");
        }
        //读取content
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        //获取相应内容对象解码器
        Decoder decoder = CommandMapping.getDecoder(command);
        //解析成内容对象
        Object content = decoder.decode(bytes);
        return new Protocol(packetLen,command,content);
    }
}
