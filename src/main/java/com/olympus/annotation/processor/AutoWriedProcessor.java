package com.olympus.annotation.processor;

import com.olympus.annotation.AutoWried;
import com.olympus.annotation.AutoWriedService;
import com.olympus.annotation.utils.AnnotationUtils;
import com.olympus.beans.BeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * @author eddie
 * @createTime 2019-02-13
 * @description 自动注入注解处理器
 */
public class AutoWriedProcessor implements AutoWriedService {

    private BeanFactory factory;

    /**
     * 获取列上的注解 如果有AutoWried注解 则处理
     * @param clazz
     * @return
     * @throws NoSuchMethodException
     */
    private void classForAnnotation0(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
          field.setAccessible(true);
          AutoWried autoWried = field.getAnnotation(AutoWried.class);
          if (autoWried != null){
              Object injectBean = factory.getBean(field.getType());
              if (Objects.isNull(injectBean)){
                  throw new RuntimeException("没有找到对应的Bean异常");
              }
              Object bean = factory.getBean(clazz);
              try {
                  field.set(bean, injectBean);
              } catch (IllegalAccessException e) {
                  e.printStackTrace();
              }
          }
        }
    }


    @Override
    public void start(String pkg, BeanFactory factory) {
        this.factory = factory;
        final List<Class<?>> allClassByPackageName = AnnotationUtils.getAllClassByPackageName(pkg);
        for (Class<?> clazz: allClassByPackageName){
            classForAnnotation0(clazz);
        }
    }
}
