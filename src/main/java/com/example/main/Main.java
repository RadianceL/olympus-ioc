package com.example.main;

import com.example.annotation.Active;
import com.example.annotation.processor.DefaultBeanProcessor;
import com.example.application.impl.ClassPathJsonApplicationContext;
import com.example.beans.BeanRegister;
import com.example.beans.impl.DefaultBeanFactory;
import com.example.main.bean.TestBean1;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 主程序入口
 */
public class Main {

    public static void main(String[] args) {
        ClassPathJsonApplicationContext context = new ClassPathJsonApplicationContext("el-default.json");
        Active active = new DefaultBeanProcessor();
        BeanRegister register= DefaultBeanFactory.getInstance();
        active.activate(TestBean1.class.getPackage(), register);
        final TestBean1 bean = context.getBean(TestBean1.class);
        System.out.println(bean.hashCode());
        System.out.println(bean.hashCode());
    }

}