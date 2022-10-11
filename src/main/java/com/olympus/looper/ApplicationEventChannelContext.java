package com.olympus.looper;

import com.olympus.looper.event.ApplicationLifecycleEvent;
import com.olympus.looper.lifecycle.LifecycleEnums;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统事件管道上下文
 * since 10/10/22
 *
 * @author eddie
 */
@Slf4j
public class ApplicationEventChannelContext extends ApplicationLifecycleEvent{
    /**
     * 单例管道
     */
    private static volatile ApplicationEventChannelContext applicationEventChannelContext;
    /**
     * 全局唯一Vertx对象
     */
    private final Vertx vertx;
    /**
     * 事件总线
     */
    private final EventBus eventBus;

    private ApplicationEventChannelContext() {
        // 初始化全局vertx对象
        vertx = Vertx.vertx();
        eventBus = vertx.eventBus();
        this.initialization();
    }

    /**
     * 初始化生命周期函数
     */
    public void initialization() {
        eventBus.consumer(LifecycleEnums.START_INITIALIZATION.getAddress(), applicationStartInitialization());
        eventBus.consumer(LifecycleEnums.START_INITIALIZATION_BEAN.getAddress(), applicationStartInitializationBean());
        eventBus.consumer(LifecycleEnums.START_ASSEMBLING_BEAN.getAddress(), applicationStartAssemblingBean());
        eventBus.consumer(LifecycleEnums.START_HAS_BEEN_INITIALIZED.getAddress(), applicationStartHasBeenInitialized())
                .completionHandler(event -> log.debug("application.lifecycle.init.finish"));
        eventBus.consumer(LifecycleEnums.APPLICATION_INITIALIZED.getAddress(), applicationInitialized());
    }

    public void applicationLifecycleChange(LifecycleEnums lifecycleEnums) {
        eventBus.publish(lifecycleEnums.getAddress(), lifecycleEnums);
    }

    public static ApplicationEventChannelContext getInstance() {
        if (applicationEventChannelContext == null) {
            synchronized (ApplicationEventChannelContext.class) {
                if (applicationEventChannelContext == null) {
                    applicationEventChannelContext = new ApplicationEventChannelContext();
                }
            }
        }
        return applicationEventChannelContext;
    }

}
