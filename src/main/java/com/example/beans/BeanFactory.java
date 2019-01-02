package com.example.beans;

import com.example.exception.BeansException;
import com.example.exception.NoSuchBeanDefinitionException;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description
 */
public interface BeanFactory {

    /**
     * 获取一个Bean
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据类型获取Bean
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;

    /**
     * 是否包含一个Bean
     * @param name
     * @return
     */
    boolean containsBean(String name);

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
