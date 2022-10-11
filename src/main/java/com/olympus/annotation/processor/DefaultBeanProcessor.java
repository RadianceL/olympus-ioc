package com.olympus.annotation.processor;

import com.olympus.application.Active;
import com.olympus.annotation.Component;
import com.olympus.annotation.utils.AnnotationUtils;
import com.olympus.beans.BeanRegister;
import com.olympus.beans.impl.DefaultBeanDefinition;
import com.olympus.looper.ApplicationEventChannelContext;
import com.olympus.looper.lifecycle.LifecycleEnums;
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
    /**
     * 获取类上的注解 如果有Bean注解 则处理
     * @param clazz         对象类型
     * @return  bean的定义
     */
    private DefaultBeanDefinition classForAnnotation0(Class<?> clazz) {
        Annotation[] classAnnotation = clazz.getAnnotations();
        for(Annotation annotation : classAnnotation){
            Class<?> annotationType =  annotation.annotationType();
            if (annotationType.equals(Component.class)){
                final String aliasesName = ((Component) annotation).value();
                return DefaultBeanDefinition.initBeanDefinition(aliasesName, clazz, ((Component) annotation).delay());
            }
        }
        return null;
    }

    @Override
    public void activate(String pkg, BeanRegister register) {
        ApplicationEventChannelContext.getInstance().applicationLifecycleChange(LifecycleEnums.START_INITIALIZATION_BEAN);
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
