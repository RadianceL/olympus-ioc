package com.olympus.looper;

import com.olympus.looper.call.AbstractCompletableFuturePool;
import com.olympus.looper.event.EventMessage;

import java.util.concurrent.CompletableFuture;

/**
 * 事件执行器
 * since 10/10/22
 *
 * @author eddie
 */
public class EventGroupWorker extends AbstractCompletableFuturePool<EventMessage<?>> {

    public EventGroupWorker(int corePoolSize, String poolName) {
        super(corePoolSize, poolName);
    }

    @Override
    public CompletableFuture<EventMessage<?>> handleCompletableFuture(EventMessage<?> data) {
        return null;
    }

    @Override
    public void futureCompleteCallback(EventMessage<?> source, Throwable throwable) {

    }

}
