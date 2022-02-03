package com.equator.netty.geekbang.client.codec;

import com.equator.netty.geekbang.common.RequestMessage;
import com.equator.netty.geekbang.common.ResponseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * 二次编解码
 *
 * @Author: Equator
 * @Date: 2022/2/3 8:34
 **/

public class OrderProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.decode(byteBuf);
        out.add(responseMessage);
    }
}
