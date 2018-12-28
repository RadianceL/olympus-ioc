package com.example.annotation;

import java.lang.annotation.*;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {
}
