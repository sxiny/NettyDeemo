package com.zpark.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServerBootstrap {

    public static void main(String[] args) throws InterruptedException {
        //1.创建服务启动引导
        ServerBootstrap sbt = new ServerBootstrap();
        //2.创建服务转发响应处理的线程池
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        //3.关联线程池
        sbt.group(boss,work);
        //4.设置服务的实现类
        sbt.channel(NioServerSocketChannel.class);
        //5.设置初始化通讯管道
        sbt.childHandler(new ServerChannelInitializer());
        System.out.println("我在9998服务器监听...");
        ChannelFuture future = sbt.bind(9998).sync();
        //7.关闭服务
        future.channel().closeFuture().sync();
        //8.释放资源
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

}
