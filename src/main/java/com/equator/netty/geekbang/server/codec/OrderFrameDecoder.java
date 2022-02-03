package com.equator.netty.geekbang.server.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 一次编解码，得到没有占包与半包的ByteBuffer
 * @Author: Equator
 * @Date: 2022/2/3 8:30
 **/

public class OrderFrameDecoder extends LengthFieldBasedFrameDecoder {
    public OrderFrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2, 0, 2);
    }
}
