package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Passenger extends Person {
    private boolean carSickness;

    public Passenger(String name, int age, String destination, boolean carSickness) {
        super(name, age, destination);
        this.carSickness = carSickness;
    }

    public boolean isCarSickness() {
        return carSickness;
    }

    public void setCarSickness(boolean carSickness) {
        this.carSickness = carSickness;
    }
}