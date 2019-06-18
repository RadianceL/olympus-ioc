package com.olympus.beans;

import com.olympus.beans.impl.DefaultBeanDefinition;

/**
 * @author eddie
 * @createTime 2019-01-02
 * @description 注册器
 */
public interface BeanRegister {

    /**
     * Bean注册
     * @param name
     * @param defaultBeanDefinition
     */
    void registerBeanDefinition(String name, DefaultBeanDefinition defaultBeanDefinition);

    /**
     * Bean注册
     * @param defaultBeanDefinition
     */
    void registerBeanDefinition(DefaultBeanDefinition defaultBeanDefinition);

    /**
     * 通过class注册Bean
     * @param clazz
     */
    void registerBeanDefinition(Class<?> clazz);
}
