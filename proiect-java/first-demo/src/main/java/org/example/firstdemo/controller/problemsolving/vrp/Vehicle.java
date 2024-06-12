package org.example.firstdemo.controller.problemsolving.vrp;

public class Vehicle {
    private int source;
    private int id;
    private String model;
    private String licensePlate;
    public Vehicle() {
        model=""; licensePlate=""; source=0; id=0;
    }
    public Vehicle(int iD, int depo){
        source=depo; id=iD;
    }
    public Vehicle(int depo, String strModel, String strLicense){
        source=depo;
        model=strModel;
        licensePlate=strLicense;
    }

    public int getSource() {
        return source;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSource(int source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "source=" + source +
                ", model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
    public void printData() {
        System.out.println(toString());
    }
    @Override
    public boolean equals(Object B) {
        return this.toString().equals(B.toString());
    }

    public Integer getVehId() {
        return id;
    }
}