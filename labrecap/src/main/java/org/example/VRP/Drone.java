package org.example.VRP;

public class Drone extends Vehicle{
    public Drone(int visClientsReq, Depot A) {
        super(visClientsReq, A);
        type="Drone";
    }
}
