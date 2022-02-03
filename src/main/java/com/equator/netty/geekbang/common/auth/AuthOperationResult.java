package com.equator.netty.geekbang.common.auth;

import com.equator.netty.geekbang.common.OperationResult;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Equator
 * @Date: 2022/2/3 0:17
 **/
@AllArgsConstructor
@Data
public class AuthOperationResult extends OperationResult {
    /**
     * 是否通过鉴权
     */
    private boolean passAuth;
}
