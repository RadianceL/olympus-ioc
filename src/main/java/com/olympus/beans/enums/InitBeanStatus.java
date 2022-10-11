package com.olympus.beans.enums;

/**
 * 初始化BEAN状态
 * since 10/10/22
 *
 * @author eddie
 */
public enum InitBeanStatus {
    /**
     * 初始化
     */
    INITIALIZED,
    /**
     * 缺少BEAN
     */
    MISSING_BEAN,
    /**
     * 初始化未知失败
     */
    INITIALIZED_FAILED
}
