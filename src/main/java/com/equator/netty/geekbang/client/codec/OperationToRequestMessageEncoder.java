package com.equator.netty.geekbang.client.codec;

import com.equator.netty.geekbang.common.Operation;
import com.equator.netty.geekbang.common.RequestMessage;
import com.equator.netty.geekbang.common.order.OrderOperation;
import com.equator.util.IdUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @Author: Equator
 * @Date: 2022/2/3 10:51
 **/

public class OperationToRequestMessageEncoder extends MessageToMessageDecoder<Operation> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Operation operation, List<Object> out) throws Exception {
        RequestMessage requestMessage = new RequestMessage(IdUtil.nextId(), operation);

        out.add(requestMessage);
    }
}
