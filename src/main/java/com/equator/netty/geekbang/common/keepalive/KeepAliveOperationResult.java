package com.equator.netty.geekbang.common.keepalive;

import com.equator.netty.geekbang.common.OperationResult;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Equator
 * @Date: 2022/2/3 0:21
 **/
@Data
@AllArgsConstructor
public class KeepAliveOperationResult extends OperationResult {
    private long time;
}
