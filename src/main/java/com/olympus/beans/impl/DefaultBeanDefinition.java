package com.olympus.beans.impl;

import com.olympus.beans.BeanDefinition;
import com.olympus.beans.enums.InitBeanStatus;
import com.olympus.exception.BeansException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author eddie
 * @createTime 2019-01-02
 * @description bean对象容器
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class DefaultBeanDefinition implements BeanDefinition {

    /**
     * bean别名
     */
    private String aliasesName;
    /**
     * 归属Class
     */
    private Class<?> targetClass;
    /**
     * bean实例
     */
    private Object bean;
    /**
     * 是否延时加载
     */
    private boolean delay;
    /**
     * 是否就绪
     */
    private boolean ready = false;

    private DefaultBeanDefinition(String targetClass, Class<?> cls, boolean delay){
        this.aliasesName = targetClass;
        this.targetClass = cls;
        this.delay = delay;
        if (!delay) {
            try {
                InitBeanStatus initBeanStatus = initBeanObject(cls);
                if (InitBeanStatus.INITIALIZED == initBeanStatus) {
                    log.debug("{} initialized", cls.getTypeName());
                }else {
                    log.debug("{} failed, case: {}", cls.getTypeName(), initBeanStatus);
                }
            }catch (Throwable throwable) {
                log.error("init bean object error", throwable);
            }
            this.ready = Objects.nonNull(this.bean);
        }
    }

    public InitBeanStatus initBeanObject(Class<?> cls) throws Throwable {
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        // 此处应该改为使用注解标记哪个构造器是注入构造器
        Parameter[] genericParameterTypes = declaredConstructors[0].getParameters();
        if (Objects.nonNull(genericParameterTypes) && genericParameterTypes.length != 0) {
            List<Class<?>> parameterClassList = Arrays.stream(genericParameterTypes)
                    .map(Parameter::getType)
                    .collect(Collectors.toList());
            DefaultBeanFactory defaultBeanFactory = DefaultBeanFactory.getInstance();
            for (Class<?> parameterClass : parameterClassList) {
                if (!defaultBeanFactory.containsBean(parameterClass)) {
                    return InitBeanStatus.MISSING_BEAN;
                }
            }
            List<Object> parameterObjects = new ArrayList<>();
            for (Parameter parameter : genericParameterTypes) {
                Object targetBean = defaultBeanFactory.getBean(parameter.getType());
                parameterObjects.add(targetBean);
            }
            this.bean = ConstructorUtils.invokeConstructor(cls, parameterObjects.toArray());
        }else {
            this.bean =  cls.getDeclaredConstructor().newInstance();
        }
        this.ready = Objects.nonNull(this.bean);
        return this.ready ? InitBeanStatus.INITIALIZED : InitBeanStatus.INITIALIZED_FAILED;
    }

    public static DefaultBeanDefinition initBeanDefinition(Class<?> cls, boolean delay) {
        String aliasesName = cls.getTypeName();
        return new DefaultBeanDefinition(aliasesName, cls, delay);
    }

    public static DefaultBeanDefinition initBeanDefinition(String aliasesName, Class<?> cls, boolean delay) {
        if (Objects.isNull(cls)) {
            throw new BeansException("initBeanDefinition class can not be null");
        }
        if (StringUtils.isBlank(aliasesName)) {
            aliasesName = cls.getTypeName();
        }
        return new DefaultBeanDefinition(aliasesName, cls, delay);
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public Object getBean() {
        if (isReady()) {
            return this.bean;
        }
        try {
            initBeanObject(this.targetClass);
        } catch (Throwable e) {
            throw new BeansException("init bean error", e);
        }

        if (isReady()) {
            return this.bean;
        }
        throw new BeansException("unknown error: can not find target bean object");
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

    @Override
    public boolean isDelay() {
        return this.delay;
    }

    @Override
    public boolean isReady() {
        return this.ready;
    }
}
