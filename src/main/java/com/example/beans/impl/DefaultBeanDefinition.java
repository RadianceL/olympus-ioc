package com.example.beans.impl;

import com.example.beans.BeanDefinition;
import com.example.exception.BeansException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;

/**
 * @author eddie
 * @createTime 2019-01-02
 * @description bean对象容器
 */
@NoArgsConstructor
@AllArgsConstructor
public class DefaultBeanDefinition implements BeanDefinition {

    private Object bean;

    public static DefaultBeanDefinition initBeanDefinition(Class<?> clazz){
        try {
            final Object o = clazz.getDeclaredConstructor().newInstance();
            return new DefaultBeanDefinition(o);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException(e.getMessage());
        }
    }

    @Override
    public Object getBean() {
        return this.bean;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
