package com.equator.netty.geekbang.client.dispatcher;

import com.equator.netty.geekbang.common.OperationResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 响应分发模型
 *
 * @Author: Equator
 * @Date: 2022/2/3 11:00
 **/
@Data
public class RequestPendingCenter {

    private Map<Long, OperationResultFuture> futureMap = new ConcurrentHashMap<>();

    /**
     * 请求时调用
     *
     * @param streamId
     * @param future
     */
    public void add(long streamId, OperationResultFuture future) {
        futureMap.put(streamId, future);
    }

    /**
     * 响应结果达到时调用
     *
     * @param streamId
     * @param operationResult
     */
    public void set(long streamId, OperationResult operationResult) {
        OperationResultFuture future = futureMap.get(streamId);
        if (future != null) {
            future.setSuccess(operationResult);
            futureMap.remove(streamId);
        }
    }
}
