package com.equator.netty.geekbang.common.auth;

import com.equator.netty.geekbang.common.Operation;
import com.equator.netty.geekbang.common.OperationResult;
import lombok.Data;

/**
 * @Author: Equator
 * @Date: 2022/2/3 0:16
 **/
@Data
public class AuthOperation extends Operation {

    private String userName;

    private String password;

    @Override
    public OperationResult execute() {
        if ("admin".equals(userName) && "123456".equals(password)) {
            return new AuthOperationResult(true);
        }
        return new AuthOperationResult(false);
    }
}
