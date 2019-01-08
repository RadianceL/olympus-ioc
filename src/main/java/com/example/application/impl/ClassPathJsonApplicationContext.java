package com.example.application.impl;

import com.example.annotation.Active;
import com.example.annotation.processor.DefaultBeanProcessor;
import com.example.application.AbstractApplicationContext;
import com.example.application.ApplicationContext;
import com.example.beans.BeanFactory;
import com.example.beans.BeanRegister;
import com.example.beans.impl.DefaultBeanFactory;
import com.example.config.ResourceParser;
import com.example.main.bean.TestBean1;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 基于JSON配置文件生成的上下文
 */
public class ClassPathJsonApplicationContext extends AbstractApplicationContext implements ApplicationContext{

    private static final Active active;

    private ResourceParser parser;

    static {
        active = new DefaultBeanProcessor();
    }

    public ClassPathJsonApplicationContext (String configPath){
        parser = new ResourceParser(configPath);
        start();
    }

    @Override
    public BeanFactory getBeanFactory() {
        return DefaultBeanFactory.getInstance();
    }

    @Override
    public void start() {
        BeanRegister register= DefaultBeanFactory.getInstance();
        active.activate(parser.getBaseBeanPackage(), register);
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
