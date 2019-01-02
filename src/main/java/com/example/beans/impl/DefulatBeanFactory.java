package com.example.beans.impl;

import com.example.beans.BeanDefinition;
import com.example.beans.BeanFactory;
import com.example.exception.BeansException;
import com.example.exception.NoSuchBeanDefinitionException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 默认Bean工厂实现
 */
public class DefulatBeanFactory implements BeanFactory {
    /** Map of bean definition objects, keyed by bean name. */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    private final Map<String, String> aliasesDefinitionMap = new ConcurrentHashMap<>(256);

    @Override
    public Object getBean(String name) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return null;
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public String[] getAliases(String name) {
        return new String[0];
    }

}
