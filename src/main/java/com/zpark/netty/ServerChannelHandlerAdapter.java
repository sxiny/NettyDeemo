package com.zpark.netty;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
public class ServerChannelHandlerAdapter extends ChannelHandlerAdapter{

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("错误："+cause.getMessage());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        /*ByteBuf buf = (ByteBuf)msg;
        System.out.println("服务器收到请求···："+buf.toString(CharsetUtil.UTF_8));
        ChannelFuture channelFuture = ctx.writeAndFlush(msg);
        channelFuture.addListener(ChannelFutureListener.CLOSE);*/
        System.out.println("服务器收到请求："+msg);
        ChannelFuture channelFuture = ctx.writeAndFlush(msg);
        channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }
}
