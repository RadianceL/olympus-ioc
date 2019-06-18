package com.olympus.exception;

import com.olympus.exception.base.BasicNestedRuntimeException;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 未找到Bean异常
 */
public class NoSuchBeanDefinitionException extends BasicNestedRuntimeException {

    public NoSuchBeanDefinitionException(String message) {
        super(message);
    }

    public NoSuchBeanDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
