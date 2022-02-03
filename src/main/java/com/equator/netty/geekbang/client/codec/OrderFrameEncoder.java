package com.equator.netty.geekbang.client.codec;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * 一次编解码
 *
 * @Author: Equator
 * @Date: 2022/2/3 8:30
 **/

public class OrderFrameEncoder extends LengthFieldPrepender {
    public OrderFrameEncoder() {
        super(2);
    }
}
