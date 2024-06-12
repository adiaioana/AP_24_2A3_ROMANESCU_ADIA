package org.example.collectionsLab;

public class Driver extends Person {
    public Driver(String dest){
        super(dest);
    }
    @Override
    public boolean isDriver(){
        return true;
    }
    @Override
    public String toString() {
        return "Driver with "+getDestination();
    }
}
