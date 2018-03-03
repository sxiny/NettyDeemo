package com.zpark.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.commons.lang3.SerializationUtils;
import java.util.List;

public class ObjectDecoder extends MessageToMessageDecoder<ByteBuf> {

    /**
     *
     * @param ctx
     * @param msg 解码对象
     * @param out 解码的数据帧
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

        System.out.println("解码。。。");
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);

        Object obj = SerializationUtils.deserialize(bytes);
        out.add(obj);
    }
}
