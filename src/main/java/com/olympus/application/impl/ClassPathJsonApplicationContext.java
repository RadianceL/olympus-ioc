package com.olympus.application.impl;

import com.olympus.application.Active;
import com.olympus.application.AutoWriedService;
import com.olympus.annotation.processor.AutoWriedProcessor;
import com.olympus.annotation.processor.DefaultBeanProcessor;
import com.olympus.application.AbstractApplicationContext;
import com.olympus.application.ApplicationContext;
import com.olympus.beans.BeanFactory;
import com.olympus.beans.BeanRegister;
import com.olympus.beans.impl.DefaultBeanFactory;
import com.olympus.config.ConfigLoader;
import com.olympus.looper.ApplicationEventChannelContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 基于JSON配置文件生成的上下文
 */
@Slf4j
public class ClassPathJsonApplicationContext extends AbstractApplicationContext implements ApplicationContext{

    /**
     * 注册IOC容器管理的bean
     */
    private static final Active active;
    /**
     * 自动注入服务
     */
    private static final AutoWriedService autoWriedService;
    /**
     * 配置加载起
     */
    private final ConfigLoader loader;
//    /**
//     * 事件管道
//     */
//    private final ApplicationEventChannelContext applicationEventChannelContext;

    static {
        active = new DefaultBeanProcessor();
        autoWriedService = new AutoWriedProcessor();
    }

    public ClassPathJsonApplicationContext(String configPath){
        log.info("start load context - 开始加载上下文 读取配置文件");
        loader = new ConfigLoader(configPath);
        log.info("start load annotation - 根据配置文件 加载所有注解类 bean类型");
//        applicationEventChannelContext = ApplicationEventChannelContext.getInstance();
        start();
    }

    @Override
    public void start() {
        BeanRegister register = DefaultBeanFactory.getInstance();
        log.info("start load bean to IOC map - 开始注册bean对象到IOC容器");
        active.activate(loader.getBaseBeanPackage(), register);
        autoWriedService.start(loader.getBaseBeanPackage(), getBeanFactory());
    }

    @Override
    public BeanFactory getBeanFactory() {
        return DefaultBeanFactory.getInstance();
    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isRunning() {
        return true;
    }

}
