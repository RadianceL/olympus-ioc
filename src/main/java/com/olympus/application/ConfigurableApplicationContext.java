package com.olympus.application;

import com.olympus.beans.BeanFactory;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description
 */
public interface ConfigurableApplicationContext {

    /**
     * 获取BeanFactory的实现类
     * @return
     */
    BeanFactory getBeanFactory();
}
