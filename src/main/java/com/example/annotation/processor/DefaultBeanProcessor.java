package com.example.annotation.processor;

import com.example.annotation.Active;
import com.example.annotation.Bean;
import com.example.annotation.utils.AnnotationUtils;
import com.example.beans.BeanRegister;
import com.example.beans.impl.DefaultBeanDefinition;

import java.lang.annotation.Annotation;
import java.util.List;

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
    private static DefaultBeanDefinition testAnnotation(Class clazz) {
        Annotation[] classAnnotation = clazz.getAnnotations();
        for(Annotation annotation : classAnnotation){
            Class annotationType =  annotation.annotationType();
            if (annotationType.equals(Bean.class)){
                System.out.println("Class " + clazz.getName() + " has Bean annotation && value: " + ((Bean)annotation).value());
                return DefaultBeanDefinition.initBeanDefinition(clazz);
            }
            System.out.println("class annotation is:" + annotationType);
        }
        return null;
    }

    @Override
    public void activate(Package pkg, BeanRegister register) {
        final List<Class<?>> allClassByPackageName = AnnotationUtils.getAllClassByPackageName(pkg);
        for (Class<?> clazz: allClassByPackageName){
            register.registerBeanDefinition(clazz.getName(), testAnnotation(clazz));
        }
    }
}
