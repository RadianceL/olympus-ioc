package com.olympus.looper.event;

import com.olympus.looper.lifecycle.LifecycleEnums;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;

/**
 * since 10/11/22
 *
 * @author eddie
 */
@Slf4j
public class ApplicationLifecycleEvent {

    protected Handler<Message<LifecycleEnums>> applicationStartInitialization() {
        return event -> {
            log.debug("应用初始化 -> {}", event.body());
        };
    }


    protected Handler<Message<LifecycleEnums>> applicationStartInitializationBean() {
        return event -> {
            log.debug("应用初始化BEAN -> {}", event.body());
        };
    }


    protected Handler<Message<LifecycleEnums>> applicationStartAssemblingBean() {
        return event -> {
            log.debug("应用初始化 -> {}", event.body());
        };
    }


    protected Handler<Message<LifecycleEnums>> applicationStartHasBeenInitialized() {
        return event -> {
            log.debug("应用初始化 -> {}", event.body());
        };
    }


    protected Handler<Message<LifecycleEnums>> applicationInitialized() {
        return event -> {
            log.debug("应用初始化 -> {}", event.body());
        };
    }
}
