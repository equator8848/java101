package com.equator.netty.geekbang.common;

import lombok.Data;

/**
 * @Author: Equator
 * @Date: 2022/2/2 23:55
 **/
@Data
public class MessageHeader {
    private int version = 1;

    private long streamId;

    private int opCode;
}
