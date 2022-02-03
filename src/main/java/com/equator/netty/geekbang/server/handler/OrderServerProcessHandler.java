package com.equator.netty.geekbang.server.handler;

import com.equator.netty.geekbang.common.Operation;
import com.equator.netty.geekbang.common.OperationResult;
import com.equator.netty.geekbang.common.RequestMessage;
import com.equator.netty.geekbang.common.ResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: Equator
 * @Date: 2022/2/3 8:36
 **/

public class OrderServerProcessHandler extends SimpleChannelInboundHandler<RequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestMessage requestMessage) throws Exception {
        Operation operation = requestMessage.getMessageBody();
        OperationResult operationResult = operation.execute();
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(requestMessage.getMessageHeader());
        responseMessage.setMessageBody(operationResult);
        channelHandlerContext.writeAndFlush(responseMessage);
    }
}
