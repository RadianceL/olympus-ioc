package com.example.application.impl;

import com.example.annotation.Active;
import com.example.annotation.AutoWriedService;
import com.example.annotation.processor.AutoWriedProcessor;
import com.example.annotation.processor.DefaultBeanProcessor;
import com.example.application.AbstractApplicationContext;
import com.example.application.ApplicationContext;
import com.example.beans.BeanFactory;
import com.example.beans.BeanRegister;
import com.example.beans.impl.DefaultBeanFactory;
import com.example.config.ConfigLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 基于JSON配置文件生成的上下文
 */
@Slf4j
public class ClassPathJsonApplicationContext extends AbstractApplicationContext implements ApplicationContext{

    private static final Active active;

    private static final AutoWriedService autoWriedService;

    private ConfigLoader loader;

    static {
        active = new DefaultBeanProcessor();
        autoWriedService = new AutoWriedProcessor();
    }

    public ClassPathJsonApplicationContext (String configPath){
        log.info("start load context - 开始加载上下文 读取配置文件");
        loader = new ConfigLoader(configPath);
        log.info("start load annotation - 根据配置文件 加载所有注解类 bean类型");
        start();
    }

    @Override
    public BeanFactory getBeanFactory() {
        return DefaultBeanFactory.getInstance();
    }

    @Override
    public void start() {
        BeanRegister register= DefaultBeanFactory.getInstance();
        log.info("start load bean to IOC map - 开始注册bean对象到IOC容器");
        active.activate(loader.getBaseBeanPackage(), register);
        autoWriedService.start(loader.getBaseBeanPackage(), getBeanFactory());
    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
