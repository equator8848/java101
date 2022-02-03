package com.equator.netty.geekbang.common;

/**
 * @Author: Equator
 * @Date: 2022/2/3 0:09
 **/

public abstract class Operation extends MessageBody {
    /**
     *
     * @return
     */
    public abstract OperationResult execute();
}
