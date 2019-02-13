package com.example.annotation.processor;

import com.example.annotation.Active;
import com.example.annotation.Bean;
import com.example.annotation.utils.AnnotationUtils;
import com.example.beans.BeanRegister;
import com.example.beans.impl.DefaultBeanDefinition;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author eddie
 * @createTime 2019-01-08
 * @description 默认Bean注解处理器
 */
@Slf4j
public class DefaultBeanProcessor implements Active {

    private static AtomicInteger count = new AtomicInteger(0);

    /**
     * 获取类上的注解 如果有Bean注解 则处理
     * @param clazz
     * @return
     * @throws NoSuchMethodException
     */
    private DefaultBeanDefinition classForAnnotation0(Class clazz) {
        Annotation[] classAnnotation = clazz.getAnnotations();
        for(Annotation annotation : classAnnotation){
            Class annotationType =  annotation.annotationType();
            if (annotationType.equals(Bean.class)){
                final int size = count.incrementAndGet();
                final String value = ((Bean) annotation).value();
                log.info("记载第 {} 个Bean对象, 别名为 {}", size, value);
                if (!value.equals("")){
                    return new DefaultBeanDefinition(value, clazz);
                }
                return DefaultBeanDefinition.initBeanDefinition(clazz);
            }
        }
        return null;
    }

    @Override
    public void activate(String pkg, BeanRegister register) {
        final List<Class<?>> allClassByPackageName = AnnotationUtils.getAllClassByPackageName(pkg);
        for (Class<?> clazz: allClassByPackageName){
            final DefaultBeanDefinition defaultBeanDefinition = classForAnnotation0(clazz);
            if (Objects.isNull(defaultBeanDefinition)){
                continue;
            }
            register.registerBeanDefinition(defaultBeanDefinition.getAliasesName(), defaultBeanDefinition);
        }
        log.info("Bean 加载完成 容器一级初始化序列结束");
    }
}
