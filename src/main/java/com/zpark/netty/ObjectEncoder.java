package com.zpark.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.List;

public class ObjectEncoder extends MessageToMessageEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {

        System.out.println("编码...");
        ByteBuf buf = Unpooled.buffer();
        byte[] bytes = SerializationUtils.serialize((Serializable) msg);
        buf.writeBytes(bytes);
        out.add(buf);
    }
}
