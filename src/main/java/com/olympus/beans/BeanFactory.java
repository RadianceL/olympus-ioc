package com.olympus.beans;

import com.olympus.exception.BeansException;
import com.olympus.exception.NoSuchBeanDefinitionException;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description
 */
public interface BeanFactory {

    /**
     * 获取一个Bean
     * @param name          需要的bean名称
     * @return              返回对应的实例
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据类型获取Bean
     * @param requiredType      需要的类型
     * @param <T>               返回对应的实例
     * @throws BeansException
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;

    /**
     * 是否包含一个Bean
     * @param name
     */
    boolean containsBean(String name);

    /**
     * 是否包含一个Bean
     * @param requireType
     */
    boolean containsBean(Class<?> requireType);

    /**
     * 该Bean是否未单例模式
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

    /**
     * 是否为原型
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    boolean isPrototype(String name) throws NoSuchBeanDefinitionException;

    /**
     * 类型是否匹配
     * @param name
     * @param typeToMatch
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException;

    /**
     * 获取类型
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    Class<?> getType(String name) throws NoSuchBeanDefinitionException;

    /**
     * 获取别名
     * @param name
     * @return
     */
    String getAliases(String name);

}
