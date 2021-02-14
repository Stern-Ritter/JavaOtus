package ru.otus.test;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RunClass {

    public static void main(String[] args) {
        List<Method> beforeMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();
        int successTestsCount = 0;

        Class<TestClass> clazz = TestClass.class;
        Constructor<TestClass> constructor = null;
        try {
            constructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            System.out.printf("В классе: %s отсутствует конструктор без параметров.\n", clazz.getSimpleName());
            return;
        }

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
        }

        beforeMethods.sort(Comparator.comparingInt(method -> method.getAnnotation(Before.class).order()));
        afterMethods.sort(Comparator.comparingInt(method -> method.getAnnotation(After.class).order()));

        for (Method testMethod : testMethods) {
            TestClass testClass = null;
            try {
                testClass = constructor.newInstance();
                for (Method beforeMethod : beforeMethods) {
                    beforeMethod.invoke(testClass);
                }
                testMethod.invoke(testClass);
                successTestsCount++;
            } catch (Exception e) {
                System.out.printf("В ходе выполнения теста %s() произошла ошибка.\n", testMethod.getName());
            } finally {
                for (Method afterMethod : afterMethods) {
                    try {
                        afterMethod.invoke(testClass);
                    } catch (Exception ignored) {
                    }
                }
            }
        }

        System.out.printf("Тестирование завершено. Выполнено успешно %d/%d методов.\n", successTestsCount, testMethods.size());
    }
}
