package com.olympus.application.impl;

import com.olympus.annotation.processor.AutoWriedProcessor;
import com.olympus.annotation.processor.DefaultBeanProcessor;
import com.olympus.application.AbstractApplicationContext;
import com.olympus.application.ApplicationContext;
import com.olympus.beans.BeanFactory;
import com.olympus.beans.BeanRegister;
import com.olympus.beans.impl.DefaultBeanFactory;
import com.olympus.config.ConfigLoader;
import com.olympus.looper.ApplicationEventChannelContext;
import com.olympus.looper.lifecycle.LifecycleEnums;
import lombok.extern.slf4j.Slf4j;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 基于JSON配置文件生成的上下文
 */
@Slf4j
public class ClassPathJsonApplicationContext extends AbstractApplicationContext implements ApplicationContext{
    /**
     * 配置加载起
     */
    private final ConfigLoader loader;
    /**
     * 事件管道
     */
    private final ApplicationEventChannelContext applicationEventChannelContext;

    public ClassPathJsonApplicationContext(String configPath){
        log.info("start load context - 开始加载上下文 读取配置文件");
        loader = new ConfigLoader(configPath);
        log.info("start load annotation - 根据配置文件 加载所有注解类 bean类型");
        applicationEventChannelContext = ApplicationEventChannelContext.getInstance();
        start();
    }

    @Override
    public void start() {
        BeanRegister register = DefaultBeanFactory.getInstance();
        applicationEventChannelContext.applicationLifecycleChange(LifecycleEnums.START_INITIALIZATION);
        // 注册IOC容器管理的bean
        new DefaultBeanProcessor().activate(loader.getBaseBeanPackage(), register);
        // bean容器组装
        ApplicationEventChannelContext.getInstance().applicationLifecycleChange(LifecycleEnums.START_ASSEMBLING_BEAN);
        // 自动注入服务
        new AutoWriedProcessor().start(loader.getBaseBeanPackage(), getBeanFactory());
        applicationEventChannelContext.applicationLifecycleChange(LifecycleEnums.APPLICATION_INITIALIZED);
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
