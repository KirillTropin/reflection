package org.example;

import org.example.annotations.AfterEach;
import org.example.annotations.BeforeEach;
import org.example.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Object cl = new ProstoClass();
        Class<?> prost  = cl.getClass();
        Method[] methods = prost.getMethods();
        Arrays.stream(methods).forEach(method -> {
            if (method.isAnnotationPresent(BeforeEach.class)){
                callBack(method, cl);
            } else if(method.isAnnotationPresent(Test.class)){
                callBack(method, cl);
            } else if (method.isAnnotationPresent(AfterEach.class)) {
                callBack(method, cl);
            }
        });
    }

    public static void callBack(Method method, Object cl){
        try {
            method.setAccessible(true);
            method.invoke(cl);
            method.setAccessible(false);
        } catch (IllegalAccessException|InvocationTargetException  e) {
            throw new RuntimeException(e);
        }
    }
}

