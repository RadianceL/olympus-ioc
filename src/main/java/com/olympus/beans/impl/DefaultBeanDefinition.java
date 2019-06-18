package com.olympus.beans.impl;

import com.olympus.beans.BeanDefinition;
import com.olympus.exception.BeansException;
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

    private String aliasesName;

    private Object bean;

    private DefaultBeanDefinition(Object bean){
        this.bean = bean;
    }

    public DefaultBeanDefinition(String aliasesName, Class<?> clz){
        this.aliasesName = aliasesName;
        try {
            this.bean = clz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

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
    public String getAliasesName() {
        if (aliasesName == null || aliasesName.equals("")){
            return bean.getClass().getName();
        }
        return aliasesName;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
