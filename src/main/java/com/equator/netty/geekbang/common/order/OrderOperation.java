package com.equator.netty.geekbang.common.order;

import com.equator.netty.geekbang.common.Operation;
import com.equator.netty.geekbang.common.OperationResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Equator
 * @Date: 2022/2/3 0:24
 **/
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOperation extends Operation {
    private int tableId;

    private String dish;

    @Override
    public OperationResult execute() {
        log.info("handle order {}", toString());
        return new OrderOperationResult(tableId, dish, true);
    }
}
