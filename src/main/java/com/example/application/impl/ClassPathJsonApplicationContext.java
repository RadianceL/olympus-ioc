package com.example.application.impl;

import com.example.application.AbstractApplicationContext;
import com.example.application.ApplicationContext;
import com.example.beans.BeanFactory;
import com.example.beans.impl.DefaultBeanFactory;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 基于JSON配置文件生成的上下文
 */
public class ClassPathJsonApplicationContext extends AbstractApplicationContext implements ApplicationContext{

    private BeanFactory factory = new DefaultBeanFactory();



    @Override
    public BeanFactory getBeanFactory() {
        return factory;
    }


}
