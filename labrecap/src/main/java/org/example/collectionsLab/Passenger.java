package org.example.collectionsLab;

public class Passenger extends Person{
    public Passenger(String dest) {
        super(dest);
    }
    @Override
    public boolean isPassenger(){
        return true;
    }
    @Override
    public String toString() {
        return "Passenger with "+getDestination();
    }
}
