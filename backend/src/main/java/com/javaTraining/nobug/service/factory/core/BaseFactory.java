package com.javaTraining.nobug.service.factory.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BaseFactory<T extends FactoryService<?>> implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<FactoryType, T> factoryMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {

        Type genericSuperClass = this.getClass().getGenericSuperclass();
        if (!(genericSuperClass instanceof ParameterizedType)) {
            throw new RuntimeException("类型不对");
        }

        Type[] args = ((ParameterizedType) genericSuperClass).getActualTypeArguments();
        if (Objects.isNull(args) || args.length != 1) {
            throw new IllegalArgumentException("actual type arguments is illegal");
        }

        Class<T> zClass = (Class<T>) args[0];
        Map<String, T> beansOfType = this.applicationContext.getBeansOfType(zClass);
        for (Map.Entry<String, T> entry : beansOfType.entrySet()) {
            T bean = entry.getValue();
            this.factoryMap.put(bean.supportType(), bean);
        }
        System.out.println(this.factoryMap);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public T getTypeBean(FactoryType factoryType) {
        return this.factoryMap.get(factoryType);
    }
}
