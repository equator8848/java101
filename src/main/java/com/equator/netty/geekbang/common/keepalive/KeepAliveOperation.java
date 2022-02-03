package com.equator.netty.geekbang.common.keepalive;

import com.equator.netty.geekbang.common.Operation;
import com.equator.netty.geekbang.common.OperationResult;
import lombok.Data;

/**
 * @Author: Equator
 * @Date: 2022/2/3 0:21
 **/
@Data
public class KeepAliveOperation extends Operation {
    private long time;

    public KeepAliveOperation() {
        this.time = System.nanoTime();
    }

    @Override
    public OperationResult execute() {
        return new KeepAliveOperationResult(time);
    }
}
