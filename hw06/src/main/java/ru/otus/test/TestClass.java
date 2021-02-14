package ru.otus.test;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestClass {

    @Before(order = 100)
    public void firstBefore() {
        System.out.println("Выполнен метод firstBefore().");
        //throw new RuntimeException();
    }

    @Before(order = 200)
    public void secondBefore() {
        System.out.println("Выполнен метод secondBefore().");
        //throw new RuntimeException();
    }

    @Before(order = 300)
    public void thirdBefore() {
        System.out.println("Выполнен метод thirdBefore().");
        //throw new RuntimeException();
    }

    @Test
    public void firstTest(){
        System.out.println("Выполнен метод firstTest().");
        //throw new RuntimeException();
    }

    @Test
    public void secondTest(){
        System.out.println("Выполнен метод secondTest().");
        //throw new RuntimeException();
    }

    @Test
    public void thirdTest(){
        System.out.println("Выполнен метод thirdTest().");
        //throw new RuntimeException();
    }

    @Test
    public void fourthTest(){
        System.out.println("Выполнен метод fourthTest().");
        //throw new RuntimeException();
    }

    @After(order = 100)
    public void firstAfter(){
        System.out.println("Выполнен метод firstAfter().");
    }

    @After(order = 200)
    public void secondAfter(){
        System.out.println("Выполнен метод secondAfter().");
    }
}
