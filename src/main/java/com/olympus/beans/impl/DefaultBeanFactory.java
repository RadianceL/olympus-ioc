package com.olympus.beans.impl;

import com.olympus.beans.BeanDefinition;
import com.olympus.beans.BeanFactory;
import com.olympus.beans.BeanRegister;
import com.olympus.exception.BeansException;
import com.olympus.exception.NoSuchBeanDefinitionException;
import com.olympus.utils.Assert;

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
    public boolean containsBean(Class<?> requireType) {
        Assert.notNull(requireType, "'beanName' must not be null");
        return resolvableDependencies.containsKey(requireType);
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
        return aClass == typeToMatch;
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        Assert.notNull(name, "'beanName' must not be null");
        return typeDefinitionMap.get(name);
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
        commonPut(defaultBeanDefinition.getAliasesName(), defaultBeanDefinition);
    }

    @Override
    public void registerBeanDefinition(Class<?> clazz) {
        DefaultBeanDefinition defaultBeanDefinition = DefaultBeanDefinition.initBeanDefinition(clazz, false);
        commonPut(defaultBeanDefinition.getAliasesName(), defaultBeanDefinition);
    }

    private void commonPut(String aliases, DefaultBeanDefinition defaultBeanDefinition) {
        final String name = defaultBeanDefinition.getBean().getClass().getTypeName();

        Object oldBean;
        if (containsBean(name)){
            oldBean = beanDefinitionMap.get(name);
            if (oldBean == defaultBeanDefinition){
                return;
            }
        }

        beanDefinitionMap.put(aliases, defaultBeanDefinition);
        final Class<?> clz = defaultBeanDefinition.getBean().getClass();
        typeDefinitionMap.put(name, clz);
        aliasesDefinitionMap.put(name, aliases);
        resolvableDependencies.put(clz, defaultBeanDefinition.getBean());
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
