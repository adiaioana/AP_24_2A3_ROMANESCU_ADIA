package org.example.firstdemo.controller.problemsolving.vrp;

public class Depot {
    protected int id;
    protected int vehicleId;

    public Depot(int nr){
        id=nr;
        vehicleId =-1;
    }
    public Depot(){
        id=0;
        vehicleId =-1;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "id=" + id +
                '}';
    }


    public void printData() {
        System.out.println(toString());
    }

    @Override
    public boolean equals(Object B) {
        return this.toString().equals(B.toString());
    }
}