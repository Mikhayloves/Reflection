package ru.sber.school.reflection.deprecated;

import ru.sber.school.reflection.cacheproxy.CachedInvocationHandler;
import ru.sber.school.reflection.calculator.Calculator;
import ru.sber.school.reflection.calculator.CalculatorImpl;
import ru.sber.school.reflection.perfomanceproxy.PerformanceProxy;
import ru.sber.school.reflection.proxy.ConstansClass;
import ru.sber.school.reflection.proxy.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class MainDynamic {

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("Реализация факториала через интерфейс Calculator:");
        CalculatorImpl calculator1 = new CalculatorImpl();
        System.out.println(calculator1.calc(5));
        System.out.println("---------------------------------------");
        System.out.println();
        System.out.println("Вывод все методов вкючая приватные класса - CachedInvocationHandler");
        Class<CachedInvocationHandler> cachedInvocationHandlerClass = CachedInvocationHandler.class;
        Method[] methods = cachedInvocationHandlerClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Метод ------>" + method);
        }
        System.out.println("---------------------------------------");
        System.out.println("Вывод всех геттеров класса Person");
        Class<Person> personClass = Person.class;
        Method[] methods1 = personClass.getDeclaredMethods();
        for (Method method : methods1) {
            if (method.getName().startsWith("get")) {
                System.out.println(method);
            }
        }
        System.out.println("---------------------------------------");
        System.out.println("Проверка что все String константы имеют значение = их имени");
        System.out.println();
        Class<ConstansClass> constansClassClass = ConstansClass.class;
        Field[] fields = constansClassClass.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
                Object value = field.get(null);
                if (field.getName().equals(value)) {
                    System.out.println(field);
                }
            }
        }
        System.out.println("---------------------------------------");
        System.out.println("Кэшированный прокси");
        System.out.println();
        Calculator delegate = new CalculatorImpl();
        Calculator calculator = (Calculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CachedInvocationHandler(delegate)
        );
        run(calculator);
        System.out.println("---------------------------------------");
        System.out.println("Проверка работы метода в наносекундах");
        System.out.println();
        Calculator performanceCalc = new CalculatorImpl();
        Calculator proxyInstance = (Calculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                performanceCalc.getClass().getInterfaces(),
                new PerformanceProxy(performanceCalc)
        );
       run(proxyInstance);
    }


    private static void run(Calculator calculator) {
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(5));
        System.out.println(calculator.calc(7));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(6));
        System.out.println(calculator.calc(3));
    }

}


