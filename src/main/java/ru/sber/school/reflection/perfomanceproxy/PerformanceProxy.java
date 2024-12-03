package ru.sber.school.reflection.perfomanceproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class PerformanceProxy implements InvocationHandler {
    private Object instance;

    public PerformanceProxy(Object instance) {
        this.instance = instance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Metric.class)) {
            return method.invoke(instance, args);
        }
        long startTime = System.nanoTime();
        Object result = method.invoke(instance, args);  // Выполняем метод на реальном объекте
        long endTime = System.nanoTime();
        long finalTime = endTime - startTime;
        System.out.println("Время работы метода '"
                + method.getName() + "' составило в наносекундах: " + finalTime);

        return result;
    }
}


