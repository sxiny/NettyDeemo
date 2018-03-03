package com.zpark.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class ClientChannelHandlerAdapter extends ChannelHandlerAdapter{
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.err.println("错误："+cause.getMessage());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*ByteBuf buf = Unpooled.buffer();
        buf.writeBytes("你好，我是客户端".getBytes());
        ChannelFuture channelFuture = ctx.writeAndFlush(buf);*/

        for (int i = 0; i < 100; i++) {
            ChannelFuture channelFuture = ctx.writeAndFlush(new Date());
            channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
            channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        }




    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*ByteBuf buf = (ByteBuf)msg;
        System.out.println("客户端收到："+buf.toString(CharsetUtil.UTF_8));*/
        System.out.println(msg);
    }
}
