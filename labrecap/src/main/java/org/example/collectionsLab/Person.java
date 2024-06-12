package org.example.collectionsLab;

import org.example.zoo.Pet;

public abstract class Person {
    private String destination;

    public Person(String dest){
        destination=dest;
    }
    public boolean isDriver(){
        return false;
    }

    public String getDestination() {
        return destination;
    }
    public boolean isPassenger(){
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
