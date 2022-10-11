package com.olympus.main.bean;

import com.olympus.annotation.AutoWried;
import com.olympus.annotation.Component;

/**
 * @author eddie
 * @createTime 2019-01-08
 * @description 测试Bean注解
 */
@Component(value = "testBean1")
public class TestBean1 {

    @AutoWried
    private TestBean2 testBean2;


    public int getHashCode(){
        return testBean2.hashCode();
    }
}
