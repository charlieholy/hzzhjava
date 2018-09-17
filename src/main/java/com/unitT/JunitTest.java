package com.unitT;
import java.lang.reflect.InvocationTargetException;

public class JunitTest {
    public static void main(String args[]) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Junit.run(TestPrductor.class);
    }
}