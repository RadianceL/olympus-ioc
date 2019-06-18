package com.olympus.annotation;

import java.lang.annotation.*;

/**
 * @author eddie
 * @createTime 2019-02-13
 * @description 自动注入
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoWried {

}
