package com.olympus.application;

import com.olympus.beans.BeanRegister;

/**
 * @author eddie
 * @createTime 2019-01-08
 * @description 激活注解处理器
 */
public interface Active {

    /**
     * 激活函数
     * @param pkg
     * @param register
     */
    void activate(String pkg, BeanRegister register);

}
