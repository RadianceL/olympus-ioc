package com.olympus.application;

import com.olympus.beans.BeanFactory;

/**
 * @author eddie
 * @createTime 2019-02-13
 * @description 自动注入服务注解处理器接口
 */
public interface AutoWriedService {

    /**
     * 激活函数
     * @param pkg           base包路径
     * @param factory       对象工厂
     */
    void start(String pkg, BeanFactory factory);

}
