package com.olympus.looper.lifecycle;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 应用生命周期状态
 * since 10/11/22
 *
 * @author eddie
 */
@Getter
@AllArgsConstructor
public enum LifecycleEnums {
    /**
     * 启动初始化
     */
    START_INITIALIZATION("application.lifecycle.startup", "启动初始化"),
    /**
     * 开始注册BEAN
     */
    START_INITIALIZATION_BEAN("application.lifecycle.init.bean", "开始注册BEAN"),
    /**
     * BEAN初级初始化结束
     */
    START_ASSEMBLING_BEAN("application.lifecycle.init.assemble", "BEAN初级初始化结束"),
    /**
     * BEAN初始化结束
     */
    START_HAS_BEEN_INITIALIZED("application.lifecycle.init.finish", "BEAN初始化结束"),
    /**
     * 应用初始化结束
     */
    APPLICATION_INITIALIZED("application.lifecycle.startup.finish", "应用初始化结束");

    private final String address;

    private final String desc;

}
