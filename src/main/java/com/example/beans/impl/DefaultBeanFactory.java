package com.example.beans.impl;

import com.example.beans.BeanDefinition;
import com.example.beans.BeanFactory;
import com.example.beans.BeanRegister;
import com.example.exception.BeansException;
import com.example.exception.NoSuchBeanDefinitionException;
import com.example.utils.Assert;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 默认Bean工厂实现
 */
public class DefaultBeanFactory implements BeanFactory, BeanRegister {

    private static volatile DefaultBeanFactory beanFactory;

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    private final Map<String, String> aliasesDefinitionMap = new ConcurrentHashMap<>(256);

    private final Map<String, Class<?>> typeDefinitionMap = new ConcurrentHashMap<>(256);

    private final Map<Class<?>, Object> resolvableDependencies = new ConcurrentHashMap<>(256);

    private DefaultBeanFactory(){}

    @Override
    public Object getBean(String name) throws BeansException {
        Assert.notNull(name, "'beanName' must not be null");
        final BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        final Object bean = beanDefinition.getBean();
        if (!Objects.isNull(bean)){
            return bean;
        }
        throw new NoSuchBeanDefinitionException("no bean named " + name);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        Assert.notNull(requiredType, "'beanName' must not be null");
        return (T) resolvableDependencies.get(requiredType);
    }

    @Override
    public boolean containsBean(String name) {
        Assert.notNull(name, "'beanName' must not be null");
        return beanDefinitionMap.containsKey(name);
    }

    @Override
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        Assert.notNull(name, "'beanName' must not be null");
        final BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        return beanDefinition.isSingleton();
    }

    @Override
    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        Assert.notNull(name, "'beanName' must not be null");
        return true;
    }

    @Override
    public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
        Assert.notNull(name, "'beanName' must not be null");
        Assert.notNull(typeToMatch, "typeToMatch must not be null");
        final Class<?> aClass = typeDefinitionMap.get(name);
        if (aClass == typeToMatch){
            return true;
        }
        return false;
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        Assert.notNull(name, "'beanName' must not be null");
        final Class<?> aClass = typeDefinitionMap.get(name);
        return aClass;
    }

    @Override
    public String getAliases(String name) {
        Assert.notNull(name, "'beanName' must not be null");
        return aliasesDefinitionMap.get(name);
    }


    @Override
    public void registerBeanDefinition(String name, DefaultBeanDefinition defaultBeanDefinition) {
        Assert.notNull(name, "'beanName' must not be null");
        commonPut(name, defaultBeanDefinition);
    }

    @Override
    public void registerBeanDefinition(DefaultBeanDefinition defaultBeanDefinition) {
        Assert.notNull(defaultBeanDefinition, "'beanName' must not be null");
        commonPut("", defaultBeanDefinition);
    }

    @Override
    public void registerBeanDefinition(Class<?> clazz) {
        DefaultBeanDefinition defaultBeanDefinition = new DefaultBeanDefinition(clazz);
        commonPut("", defaultBeanDefinition);
    }

    private void commonPut(String aliases, DefaultBeanDefinition defaultBeanDefinition) {
        final String name = defaultBeanDefinition.getBean().getClass().getName();

        Object oldBean = null;
        if (containsBean(name)){
            oldBean = beanDefinitionMap.get(name);
            if (oldBean == defaultBeanDefinition){
                return;
            }
        }

        beanDefinitionMap.put(name, defaultBeanDefinition);
        final Class<?> aClass = defaultBeanDefinition.getBean().getClass();
        typeDefinitionMap.put(name, aClass);
        aliasesDefinitionMap.put(name, aliases);
        resolvableDependencies.put(aClass, defaultBeanDefinition.getBean());
    }

    public static DefaultBeanFactory getInstance() {
        if (beanFactory == null) {
            synchronized (DefaultBeanFactory.class) {
                if (beanFactory == null) {
                    beanFactory = new DefaultBeanFactory();
                }
            }
        }
        return beanFactory;
    }
}
