package org.example.firstdemo.controller.problemsolving.vrp;

import java.util.*;

public class Problem {
    Vector<Client> clients=new Vector<Client>();
    Vector<Depot> depots=new Vector<Depot>();
    Vector<Vehicle> Vehicles=new Vector<Vehicle>();
    /**
     * VehicleMap identifies Vehicles from Vector by Vehicle id (integer)
     **/
    Map <Integer, Vehicle> VehicleMap= new HashMap<Integer,Vehicle>() ;

    public void setClients(Vector<Client> clients) {
        this.clients = clients;
    }
    public Vector<Client> getClients() {
        return clients;
    }
    public Problem(Vector<Client> clis, Vector<Depot> depos, Vector<Vehicle> vehs){
        this.clients=clis;
        this.depots=depos;
        this.Vehicles=vehs;
        this.makeTheMap();
    }

    public void makeTheMap() {

        VehicleMap.clear();
        for(Vehicle veh:Vehicles){
            this.VehicleMap.put(veh.getVehId(),veh);
        }
    }
    public void setDepots(Vector<Depot> depots) {
        this.depots = depots;
    }
    public Vector<Depot> getDepots() {
        return depots;
    }
    public void setVehicles(Vector<Vehicle> Vehicles) {
        this.Vehicles = Vehicles;
        this.makeTheMap();
    }
    public Vector<Vehicle> getVehicles() {
        return Vehicles;
    }
    public Vector<Vehicle> getVehiclesFromDepo() {
        /**
         * @auxVehicles takes the Vehicles id from depots uniquely
         * @allVehicles will take the values from auxVehicles
         **/
        Set<Vehicle> auxVehicles=new HashSet<Vehicle>();
        Vector<Vehicle> allVehicles=new Vector<Vehicle>(Vehicles.size()+10);
        auxVehicles.clear();
        for(Depot dep:depots){
            Vehicle depToVec=VehicleMap.get(dep.vehicleId);
            auxVehicles.add(depToVec);
        }
        allVehicles.clear();
        for(Vehicle  veh:auxVehicles){
            allVehicles.add(veh);
        }
        return allVehicles;
    }
    public int nrClients(){
        return this.clients.size();
    }
    public int nrDepots(){
        return this.depots.size();
    }
}