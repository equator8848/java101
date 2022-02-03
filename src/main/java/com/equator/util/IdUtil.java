package com.equator.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: Equator
 * @Date: 2022/2/3 0:28
 **/

public final class IdUtil {
    private static final AtomicLong IDX = new AtomicLong();

    private IdUtil() {

    }

    public static long nextId() {
        return IDX.incrementAndGet();
    }
}
