package com.equator.netty.geekbang.common;

import com.equator.netty.geekbang.common.auth.AuthOperation;
import com.equator.netty.geekbang.common.auth.AuthOperationResult;
import com.equator.netty.geekbang.common.keepalive.KeepAliveOperation;
import com.equator.netty.geekbang.common.keepalive.KeepAliveOperationResult;
import com.equator.netty.geekbang.common.order.OrderOperation;
import com.equator.netty.geekbang.common.order.OrderOperationResult;

/**
 * @Author: Equator
 * @Date: 2022/2/3 0:12
 **/
public enum OperationType {
    /**
     * 鉴权
     */
    AUTH(1, AuthOperation.class, AuthOperationResult.class),
    /**
     * 探活
     */
    KEEP_ALIVE(2, KeepAliveOperation.class, KeepAliveOperationResult.class),
    /**
     * 业务
     */
    ORDER(3, OrderOperation.class, OrderOperationResult.class);

    private int opCode;

    private Class<? extends Operation> operationClazz;

    private Class<? extends OperationResult> operationResultClazz;

    OperationType(int opCode, Class<? extends Operation> operationClazz, Class<? extends OperationResult> operationResultClazz) {
        this.opCode = opCode;
        this.operationClazz = operationClazz;
        this.operationResultClazz = operationResultClazz;
    }

    public static OperationType fromOperation(Operation operation) {
        if (operation instanceof OrderOperation) {
            return ORDER;
        } else if (operation instanceof KeepAliveOperation) {
            return KEEP_ALIVE;
        } else {
            return AUTH;
        }
    }

    public static OperationType fromOpCode(int opCode) {
        switch (opCode) {
            case 3:
                return ORDER;
            case 2:
                return KEEP_ALIVE;
            case 1:
                return AUTH;
            default:
                return null;
        }
    }

    public int getOpCode() {
        return opCode;
    }

    public void setOpCode(int opCode) {
        this.opCode = opCode;
    }

    public Class<? extends Operation> getOperationClazz() {
        return operationClazz;
    }

    public void setOperationClazz(Class<? extends Operation> operationClazz) {
        this.operationClazz = operationClazz;
    }

    public Class<? extends OperationResult> getOperationResultClazz() {
        return operationResultClazz;
    }

    public void setOperationResultClazz(Class<? extends OperationResult> operationResultClazz) {
        this.operationResultClazz = operationResultClazz;
    }
}
