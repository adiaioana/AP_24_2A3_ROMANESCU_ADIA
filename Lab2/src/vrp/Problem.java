package vrp;

import java.util.*;

public class Problem {
    Vector<Client> clients=new Vector<Client>();
    Vector<Depot> depots=new Vector<Depot>();
    Vector<AbstractVehicle> AbstractVehicles=new Vector<AbstractVehicle>();
    /**
     * AbstractVehicleMap identifies AbstractVehicles from Vector by AbstractVehicle id (integer)
     **/
    Map <Integer, AbstractVehicle> AbstractVehicleMap= new HashMap<Integer,AbstractVehicle>() ;

    public void setClients(Vector<Client> clients) {
        this.clients = clients;
    }
    public Vector<Client> getClients() {
        return clients;
    }
    Problem(Vector<Client> clis, Vector<Depot> depos, Vector<AbstractVehicle> vehs){
        this.clients=clis;
        this.depots=depos;
        this.AbstractVehicles=vehs;
        this.makeTheMap();
    }

    public void makeTheMap() {

        AbstractVehicleMap.clear();
        for(AbstractVehicle veh:AbstractVehicles){
            this.AbstractVehicleMap.put(veh.getVehId(),veh);
        }
    }
    public void setDepots(Vector<Depot> depots) {
        this.depots = depots;
    }
    public Vector<Depot> getDepots() {
        return depots;
    }
    public void setAbstractVehicles(Vector<AbstractVehicle> AbstractVehicles) {
        this.AbstractVehicles = AbstractVehicles;
        this.makeTheMap();
    }
    public Vector<AbstractVehicle> getAbstractVehicles() {
        return AbstractVehicles;
    }
    public Vector<AbstractVehicle> getAbstractVehiclesFromDepo() {
        /**
         * @auxAbstractVehicles takes the AbstractVehicles id from depots uniquely
         * @allAbstractVehicles will take the values from auxAbstractVehicles
         **/
        Set<AbstractVehicle> auxAbstractVehicles=new HashSet<AbstractVehicle>();
        Vector<AbstractVehicle> allAbstractVehicles=new Vector<AbstractVehicle>(AbstractVehicles.size()+10);
        auxAbstractVehicles.clear();
        for(Depot dep:depots){
            AbstractVehicle depToVec=AbstractVehicleMap.get(dep.getVehicleid());
            auxAbstractVehicles.add(depToVec);
        }
        allAbstractVehicles.clear();
        for(AbstractVehicle  veh:auxAbstractVehicles){
            allAbstractVehicles.add(veh);
        }
        return allAbstractVehicles;
    }
    public int nrClients(){
        return this.clients.size();
    }
    public int nrDepots(){
        return this.depots.size();
    }
}
