package com.example.exception;

import com.example.exception.base.NestedRuntimeException;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 未找到Bean异常
 */
public class NoSuchBeanDefinitionException extends NestedRuntimeException {

    public NoSuchBeanDefinitionException(String message) {
        super(message);
    }

    public NoSuchBeanDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
