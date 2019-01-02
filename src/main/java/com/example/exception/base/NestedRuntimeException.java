package com.example.exception.base;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 基本异常
 */
public class NestedRuntimeException extends RuntimeException {

    public NestedRuntimeException(String message) {
        super(message);
    }

    public NestedRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
