package com.project.university.configuration;

import com.project.university.annotations.ExecutionLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExecutionLogBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(ExecutionLog.class)) {
            map.put(beanName, beanClass);
        } else {
            Arrays.stream(beanClass.getMethods())
                    .filter(method -> method.isAnnotationPresent(ExecutionLog.class))
                    .findFirst()
                    .ifPresent(method -> map.put(beanName, beanClass));
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = map.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    new ExecutionLogInvocationHandler(bean, beanClass));
        }

        return bean;
    }
}
