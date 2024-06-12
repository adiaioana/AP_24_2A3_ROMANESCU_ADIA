package org.example.VRP;

public class Truck extends Vehicle{
    public Truck(int visClientsReq, Depot A) {
        super(visClientsReq, A);
        type="Truck";
    }
}
