package org.example.VRP;

public abstract class Vehicle {
    protected Depot belongedDepot;
    protected int reqClients;
    public String type;

    public Vehicle(int visClientsReq, Depot A){
        belongedDepot=A;
        reqClients=visClientsReq;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
