package com.example.application.impl;

import com.example.application.AbstractApplicationContext;
import com.example.application.ApplicationContext;
import com.example.beans.BeanFactory;
import com.example.beans.impl.DefaultBeanFactory;
import com.example.config.ResourceParser;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 基于JSON配置文件生成的上下文
 */
public class ClassPathJsonApplicationContext extends AbstractApplicationContext implements ApplicationContext{

    public ClassPathJsonApplicationContext (String configPath){
        ResourceParser parser = new ResourceParser(configPath);
        start();
    }

    @Override
    public BeanFactory getBeanFactory() {
        return DefaultBeanFactory.getInstance();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
