package com.equator.netty.geekbang.client;


import com.equator.netty.geekbang.client.codec.*;
import com.equator.netty.geekbang.client.dispatcher.OperationResultFuture;
import com.equator.netty.geekbang.client.dispatcher.RequestPendingCenter;
import com.equator.netty.geekbang.client.dispatcher.ResponseDispatchHandler;
import com.equator.netty.geekbang.common.OperationResult;
import com.equator.netty.geekbang.common.RequestMessage;
import com.equator.netty.geekbang.common.order.OrderOperation;
import com.equator.util.IdUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @Author: Equator
 * @Date: 2022/2/3 10:27
 **/
@Slf4j
public class ClientV3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Bootstrap bootstrap = new Bootstrap();

        RequestPendingCenter requestPendingCenter = new RequestPendingCenter();

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

                pipeline.addLast(new ResponseDispatchHandler(requestPendingCenter));

                pipeline.addLast(new OperationToRequestMessageEncoder());
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
            }
        });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090).sync();

        long streamId = IdUtil.nextId();

        RequestMessage requestMessage = new RequestMessage(streamId, new OrderOperation(1, "tomato"));

        OperationResultFuture future = new OperationResultFuture();
        requestPendingCenter.add(streamId, future);

        channelFuture.channel().writeAndFlush(requestMessage);

        // 阻塞读取结果
        OperationResult operationResult = future.get();
        log.info("operationResult {}", operationResult);

        channelFuture.channel().closeFuture().get();
    }
}
