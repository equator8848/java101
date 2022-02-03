package com.equator.netty.geekbang.common;

import lombok.NoArgsConstructor;

/**
 * @Author: Equator
 * @Date: 2022/2/3 0:12
 **/
@NoArgsConstructor
public class RequestMessage extends Message<Operation> {
    @Override
    public Class<Operation> getMessageBodyDecodeClass(int opCode) {
        return (Class<Operation>) OperationType.fromOpCode(opCode).getOperationClazz();
    }

    public RequestMessage(long streamId, Operation operation) {
        MessageHeader header = new MessageHeader();
        header.setStreamId(streamId);
        header.setOpCode(OperationType.fromOperation(operation).getOpCode());
        this.setMessageHeader(header);
        this.setMessageBody(operation);
    }
}
