package com.example.application;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description IOC框架核心入口
 */
public interface ApplicationContext {



    /**
     * 获取一个bean
     * @param name
     * @return
     */
    Object getBean(String name);
}
