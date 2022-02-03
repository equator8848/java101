package com.equator.netty.geekbang.server.codec;

import com.equator.netty.geekbang.common.RequestMessage;
import com.equator.netty.geekbang.common.ResponseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 二次编解码
 *
 * @Author: Equator
 * @Date: 2022/2/3 8:34
 **/

public class OrderProtocolEncoder extends MessageToMessageEncoder<ResponseMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseMessage responseMessage, List<Object> out) throws Exception {
        ByteBuf buffer = channelHandlerContext.alloc().buffer();
        responseMessage.encode(buffer);
        out.add(buffer);
    }
}
