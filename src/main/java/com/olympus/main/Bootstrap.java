package com.olympus.main;

import com.olympus.application.impl.ClassPathJsonApplicationContext;
import com.olympus.main.bean.TestBean1;
import com.olympus.main.bean.TestBean2;
import lombok.extern.slf4j.Slf4j;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 主程序入口
 */
@Slf4j
public class Bootstrap {

    public static void main(String[] args) {
        ClassPathJsonApplicationContext context = new ClassPathJsonApplicationContext("el-default.json");
        final TestBean1 bean1 = (TestBean1) context.getBean("testBean1");
        final TestBean2 bean2 = context.getBean(TestBean2.class);
        log.info("通过别名获取Bean对象:{}, hasCode值为:{}", "TestBean1", bean1.hashCode());
        log.info("通过类型获取Bean对象:{}, hasCode值为:{}", "TestBean2", bean2.hashCode());
        log.info("通过AutoWried 注解获取Bean2对象:{}, hasCode值为:{}", "TestBean1", bean1.getHashCode());
    }

}