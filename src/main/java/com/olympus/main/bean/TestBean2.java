package com.olympus.main.bean;

import com.olympus.annotation.Component;

/**
 * @author eddie
 * @createTime 2019-01-08
 * @description 测试Bean注解
 */
@Component(value = "testBean2")
public class TestBean2 {

    private TestBean1 testBean1;

    public TestBean2(TestBean1 testBean1) {
        this.testBean1 = testBean1;
    }
}
