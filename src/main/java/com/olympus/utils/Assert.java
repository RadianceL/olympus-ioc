package com.olympus.utils;

/**
 * @author eddie
 * @createTime 2019-01-02
 * @description
 */
public class Assert {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
