package com.example.beans.impl;

import com.example.beans.BeanDefinition;
import lombok.AllArgsConstructor;

/**
 * @author eddie
 * @createTime 2019-01-02
 * @description bean对象容器
 */
@AllArgsConstructor
public class DefaultBeanDefinition implements BeanDefinition {

    private Object bean;

    @Override
    public Object getBean() {
        return this.bean;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
