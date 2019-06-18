package com.olympus.context;

/**
 * @author eddie
 * @createTime 2018-12-28
 * @description 生命周期
 */
public interface Lifecycle {
    /**
     * 开始 - 发出一个信号，告知注解处理器可以开始工作了
     */
    void start();

    /**
     * 结束 - 容器&&启动 结束，告知注解处理器结束工作，销毁
     */
    void stop();

    /**
     * 判断容器是否运行中
     * @return
     */
    boolean isRunning();
}
