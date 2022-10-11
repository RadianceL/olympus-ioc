package com.olympus.looper;

/**
 * 系统事件管道上下文
 * since 10/10/22
 *
 * @author eddie
 */
public class ApplicationEventChannelContext{
    private static volatile ApplicationEventChannelContext applicationEventChannelContext;

    private ApplicationEventChannelContext() {
        EventLooper eventLooper = new EventLooper();
        this.initialization();
    }

    public void initialization() {

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
