package com.zpark.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();
        //数据帧头解码器
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
       //数据帧头编码器
        pipeline.addLast(new LengthFieldPrepender(2));
        pipeline.addLast(new ObjectDecoder());//编码对象
        pipeline.addLast(new ObjectEncoder());//解码对象
        //添加最终处理者
        pipeline.addLast(new ClientChannelHandlerAdapter());

    }
}
