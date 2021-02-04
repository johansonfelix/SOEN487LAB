package com.example.rest;

import com.sun.jmx.snmp.SnmpUnknownAccContrModelException;

public class Customer {
    private final int id;
    private String name;
    private  int age;
    private static int currentId = 0;

    public Customer (String name, int age){
        this.id = currentId++;
        this.name = name;
        this.age = age;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("Hello, my ID is %s, my name is %s and I am %d years old",getId(), getName(), getAge());
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        Customer.currentId = currentId;
    }
}
