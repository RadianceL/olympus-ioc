package com.example.annotation;

import java.lang.annotation.*;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 与Spring 中的Bean注解功能相同
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {
    String value() default "";
    int count() default 0;
}
