package com.equator.netty.geekbang.common;

/**
 * @Author: Equator
 * @Date: 2022/2/3 0:13
 **/

public class ResponseMessage extends Message<OperationResult> {
    @Override
    public Class<OperationResult> getMessageBodyDecodeClass(int opCode) {
        return (Class<OperationResult>) OperationType.fromOpCode(opCode).getOperationResultClazz();
    }
}
