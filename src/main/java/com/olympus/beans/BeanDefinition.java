package com.olympus.beans;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 实例化好的Bean对象封装
 */
public interface BeanDefinition {

    /**
     * 获取Bean
     */
    Object getBean();
    /**
     * 获取别名
     */
    String getAliasesName();
    /**
     * 是否为单例模式
     */
    boolean isSingleton();
    /**
     * 是否延时加载
     */
    boolean isDelay();
    /**
     * 是否就绪
     */
    boolean isReady();

}
