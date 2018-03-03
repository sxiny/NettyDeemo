package com.zpark.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClientBootstrap {

    public static void main(String[] args) throws InterruptedException {
        //1.创建服务启动引导
        Bootstrap bt = new Bootstrap();
        //2.创建服务转发响应处理的线程池
        EventLoopGroup work = new NioEventLoopGroup();
        //3.关联线程池
        bt.group(work);
        //4.设置服务的实现类
        bt.channel(NioSocketChannel.class);
        //5.设置初始化通讯管道
        bt.handler(new ClientChannelInitializer());
        //6.绑定监听端口，启动服务
        ChannelFuture future = bt.connect("127.0.0.1",9998).sync();
        //7.关闭服务
        future.channel().closeFuture().sync();
        //8.释放资源
        work.shutdownGracefully();
    }

}
