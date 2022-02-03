package com.equator.netty.geekbang.client;


import com.equator.netty.geekbang.client.codec.OrderFrameDecoder;
import com.equator.netty.geekbang.client.codec.OrderFrameEncoder;
import com.equator.netty.geekbang.client.codec.OrderProtocolDecoder;
import com.equator.netty.geekbang.client.codec.OrderProtocolEncoder;
import com.equator.netty.geekbang.common.RequestMessage;
import com.equator.netty.geekbang.common.order.OrderOperation;
import com.equator.netty.geekbang.server.handler.OrderServerProcessHandler;
import com.equator.util.IdUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ExecutionException;

/**
 * @Author: Equator
 * @Date: 2022/2/3 10:27
 **/

public class ClientV1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.channel(NioSocketChannel.class);

        bootstrap.group(new NioEventLoopGroup());

        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast(new OrderFrameDecoder());
                pipeline.addLast(new OrderFrameEncoder());
                pipeline.addLast(new OrderProtocolEncoder());
                pipeline.addLast(new OrderProtocolDecoder());
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
            }
        });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090).sync();

        RequestMessage requestMessage = new RequestMessage(IdUtil.nextId(), new OrderOperation(1, "tomato"));

        channelFuture.channel().writeAndFlush(requestMessage);

        channelFuture.channel().closeFuture().get();
    }
}
