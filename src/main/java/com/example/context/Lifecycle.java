package com.example.context;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 生命周期
 */
public interface Lifecycle {
    /**
     * 开始
     */
    void start();

    /**
     * 结束
     */
    void stop();

    /**
     * 是否运行中
     * @return
     */
    boolean isRunning();
}
