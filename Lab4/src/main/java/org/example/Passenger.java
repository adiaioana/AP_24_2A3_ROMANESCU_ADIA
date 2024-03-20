package org.example;

class Passenger extends Person {
    private boolean carSickness;

    public Passenger(String name, int age, String destination, boolean carSickness) {
        super(name, age, destination);
        this.carSickness = carSickness;
    }

    // Getter and setter for carSickness
    public boolean isCarSickness() {
        return carSickness;
    }

    public void setCarSickness(boolean carSickness) {
        this.carSickness = carSickness;
    }
}