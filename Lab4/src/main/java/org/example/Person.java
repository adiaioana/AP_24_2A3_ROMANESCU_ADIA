package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract class Person {
    final String destinationsFile="./src/main/java/org/example/destinations.txt";
    private String name;

    private int age;
    private String destination;
    private int idDestination=0;
    private final Helper helper=new Helper();
    public int getIdDestination() {
        return idDestination;
    }
    public void setIdDestination(int K) {
        idDestination=K;
        return;
    }

    public Person(String name, int age, String destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
        this.idDestination=helper.whichId(destination);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", destination='" + destination + '\'' +
                ", idDestination=" + idDestination +
                '}';
    }
}
