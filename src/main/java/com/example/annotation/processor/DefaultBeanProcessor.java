package com.example.annotation.processor;

import com.example.annotation.Active;
import com.example.annotation.Bean;
import com.example.annotation.utils.AnnotationUtils;
import com.example.beans.BeanRegister;
import com.example.beans.impl.DefaultBeanDefinition;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;

/**
 * @author eddie
 * @createTime 2019-01-08
 * @description 默认Bean注解处理器
 */
public class DefaultBeanProcessor implements Active {

    /**
     * 获取类上的注解 如果有Bean注解 则处理
     * @param clazz
     * @return
     * @throws NoSuchMethodException
     */
    private static DefaultBeanDefinition classForAnnotation0(Class clazz) {
        Annotation[] classAnnotation = clazz.getAnnotations();
        for(Annotation annotation : classAnnotation){
            Class annotationType =  annotation.annotationType();
            if (annotationType.equals(Bean.class)){
                final String value = ((Bean) annotation).value();
                System.out.println("Class " + clazz.getName() + " has Bean annotation && value: " + value);
                if (!value.equals("")){
                    return new DefaultBeanDefinition(value, clazz);
                }
                return DefaultBeanDefinition.initBeanDefinition(clazz);
            }
        }
        return null;
    }

    @Override
    public void activate(Package pkg, BeanRegister register) {
        final List<Class<?>> allClassByPackageName = AnnotationUtils.getAllClassByPackageName(pkg);
        for (Class<?> clazz: allClassByPackageName){
            final DefaultBeanDefinition defaultBeanDefinition = classForAnnotation0(clazz);
            if (Objects.isNull(defaultBeanDefinition)){
                continue;
            }
            register.registerBeanDefinition(defaultBeanDefinition.getAliasesName(), defaultBeanDefinition);
        }
    }
}
