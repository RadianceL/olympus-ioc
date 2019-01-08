package com.example.main;

import com.example.application.impl.ClassPathJsonApplicationContext;
import com.example.main.bean.TestBean1;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 主程序入口
 */
public class Main {

    public static void main(String[] args) {
        ClassPathJsonApplicationContext context = new ClassPathJsonApplicationContext("el-default.json");
        final TestBean1 bean = (TestBean1) context.getBean("testBean1");
        final TestBean1 bean1 = context.getBean(TestBean1.class);
        System.out.println(bean.hashCode());
        System.out.println(bean1.hashCode());
    }

}