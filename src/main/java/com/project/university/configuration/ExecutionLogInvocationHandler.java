package com.project.university.configuration;

import com.project.university.annotations.ExecutionLog;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ExecutionLogInvocationHandler implements InvocationHandler {

    private final Object bean;
    private final Class<?> beanClass;

    public ExecutionLogInvocationHandler(Object bean, Class<?> beanClass) {
        this.bean = bean;
        this.beanClass = beanClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (beanClass.isAnnotationPresent(ExecutionLog.class) ||
                beanClass.getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(ExecutionLog.class)) {
            return logAndInvoke(method, args);
        } else {
            return method.invoke(bean, args);
        }
    }

    private Object logAndInvoke(Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        long before = System.nanoTime();
        Object retVal = method.invoke(bean, args);
        long after = System.nanoTime();
        log.info("Method {}() worked: {} ns!", method.getName(), (after - before));

        return retVal;
    }
}
