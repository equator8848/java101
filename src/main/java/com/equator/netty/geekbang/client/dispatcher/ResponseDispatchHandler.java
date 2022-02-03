package com.equator.netty.geekbang.client.dispatcher;

import com.equator.netty.geekbang.common.ResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

/**
 * @Author: Equator
 * @Date: 2022/2/3 11:06
 **/
@AllArgsConstructor
public class ResponseDispatchHandler extends SimpleChannelInboundHandler<ResponseMessage> {

    private RequestPendingCenter requestPendingCenter;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ResponseMessage responseMessage) throws Exception {
        requestPendingCenter.set(responseMessage.getMessageHeader().getStreamId(), responseMessage.getMessageBody());
    }
}
