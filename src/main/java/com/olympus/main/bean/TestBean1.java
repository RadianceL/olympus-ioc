package com.olympus.main.bean;

import com.olympus.annotation.AutoWried;
import com.olympus.annotation.Bean;

/**
 * @author eddie
 * @createTime 2019-01-08
 * @description 测试Bean注解
 */
@Bean(value = "testBean1")
public class TestBean1 {

    @AutoWried
    private TestBean2 testBean2;

    public int getHashCode(){
        return testBean2.hashCode();
    }
}
