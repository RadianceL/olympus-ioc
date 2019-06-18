package com.olympus.exception;

import com.olympus.exception.base.BasicNestedRuntimeException;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description
 */
public class BeansException extends BasicNestedRuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }

}
