package com.olympus.annotation;

import java.lang.annotation.*;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 与Spring 中的Bean注解功能相同
 */
@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    /**
     * 别名
     */
    String value() default "";
    /**
     * 是否延迟加载
     */
    boolean delay() default false;
}
