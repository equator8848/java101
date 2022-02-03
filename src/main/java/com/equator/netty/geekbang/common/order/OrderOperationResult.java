package com.equator.netty.geekbang.common.order;

import com.equator.netty.geekbang.common.OperationResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @Author: Equator
 * @Date: 2022/2/3 0:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOperationResult extends OperationResult {
    private int tableId;

    private String dish;

    private boolean complete;
}
