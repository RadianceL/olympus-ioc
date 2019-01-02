package com.example.exception;

import com.example.exception.base.NestedRuntimeException;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description
 */
public class BeansException extends NestedRuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }

}
