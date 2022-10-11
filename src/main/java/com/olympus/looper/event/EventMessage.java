package com.olympus.looper.event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 事件消息
 * since 10/10/22
 *
 * @author eddie
 */
@NoArgsConstructor
@AllArgsConstructor
public class EventMessage<T> implements Comparable<EventMessage<?>> {
    /**
     * 消息渠道
     */
    private String messageChannel;
    /**
     * 消息
     */
    private T message;

    @Override
    public int compareTo(EventMessage<?> o) {
        return 0;
    }
}
