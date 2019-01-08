package com.example.beans;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 实例化好的Bean对象封装
 */
public interface BeanDefinition {

    /**
     * 获取Bean
     * @return
     */
    Object getBean();

    /**
     * 获取别名
     * @return
     */
    String getAliasesName();

    /**
     * 是否为单例模式
     * @return
     */
    boolean isSingleton();

}
