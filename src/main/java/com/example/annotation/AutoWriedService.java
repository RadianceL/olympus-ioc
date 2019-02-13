package com.example.annotation;

import com.example.beans.BeanFactory;

/**
 * @author eddie
 * @createTime 2019-02-13
 * @description 自动注入服务注解处理器接口
 */
public interface AutoWriedService {

    /**
     * 激活函数
     * @param pkg
     * @param factory
     */
    void start(String pkg, BeanFactory factory);

}
