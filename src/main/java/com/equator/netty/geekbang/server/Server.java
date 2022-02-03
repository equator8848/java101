package com.equator.netty.geekbang.server;

import com.equator.netty.geekbang.server.codec.OrderFrameDecoder;
import com.equator.netty.geekbang.server.codec.OrderFrameEncoder;
import com.equator.netty.geekbang.server.codec.OrderProtocolDecoder;
import com.equator.netty.geekbang.server.codec.OrderProtocolEncoder;
import com.equator.netty.geekbang.server.handler.OrderServerProcessHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ExecutionException;

/**
 * @Author: Equator
 * @Date: 2022/2/3 9:32
 **/

public class Server {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 设置IO模型
        bootstrap.channel(NioServerSocketChannel.class);
        // 设置Reactor模式
        bootstrap.group(new NioEventLoopGroup());
        // 设置日志处理器
        bootstrap.handler(new LoggingHandler(LogLevel.INFO));
        // 设置流水线
        bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                // 注意顺序
                pipeline.addLast(new OrderFrameDecoder());
                pipeline.addLast(new OrderFrameEncoder());
                pipeline.addLast(new OrderProtocolEncoder());
                pipeline.addLast(new OrderProtocolDecoder());
                pipeline.addLast(new OrderServerProcessHandler());
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
            }
        });
        ChannelFuture channelFuture = bootstrap.bind(8090).sync();

        channelFuture.channel().closeFuture().get();
    }
}
