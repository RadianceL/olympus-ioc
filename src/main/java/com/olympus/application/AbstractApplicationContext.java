package com.olympus.application;

import com.olympus.beans.BeanFactory;
import com.olympus.application.context.Lifecycle;
import com.olympus.exception.BeansException;
import com.olympus.exception.NoSuchBeanDefinitionException;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description
 */
public abstract class AbstractApplicationContext implements Lifecycle, ConfigurableApplicationContext, BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public boolean containsBean(String name) {
        return getBeanFactory().containsBean(name);
    }

    @Override
    public boolean containsBean(Class<?> requireType) {
        return getBeanFactory().containsBean(requireType);
    }

    @Override
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return getBeanFactory().isSingleton(name);
    }

    @Override
    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return getBeanFactory().isPrototype(name);
    }

    @Override
    public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
        return getBeanFactory().isTypeMatch(name, typeToMatch);
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return getBeanFactory().getType(name);
    }

    @Override
    public String getAliases(String name) {
        return getBeanFactory().getAliases(name);
    }

    /**
     * 获取bean工厂
     * @return
     * @throws IllegalStateException
     */
    @Override
    public abstract BeanFactory getBeanFactory() throws IllegalStateException;
}
