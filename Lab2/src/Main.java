
import vrp.Client;
import vrp.Depot;
import vrp.Vehicle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public final String DIGITS="0123456789";
    public static int noClients=0;
    public static int noDepots=0;
    public static int noVehicles=0;
    public static void main(String[] args) {
        Main adia=new Main();
        adia.compulsory();
        adia.homework();
    }
    public void homework(){
/**
 * Create classes that describe the problem and its solution.
 # Override the equals method form the Object class for the Depot, Vehicle, Client classes. The problem should not allow adding the same depot, vehicle or client twice.
 # Vehicles may be of different types. Create dedicated classes for trucks and drones. Trucks have a capacity property, while drones have a maximum flight duration (these properties will not be used by the algorithms). The Vehicle class will become abstract.
 # Implement the method getVehicles in the Problem class, returning an array of all the vehicles, form all depots.
 * Assume that the times required to travel between the locations (depot-to-client or client-to-client) are known (You may generate them randomly). Implement a simple greedy algorithm for allocating clients to vehicles.
 * Write doc comments in your source code and generate the class documentation using javadoc.
 **/
    }
    public void compulsory() {
        Vector<Client> clients=new Vector<Client>();
        Vector<Depot> depots=new Vector<Depot>();
        Vector<Vehicle> vehicles=new Vector<Vehicle>();

        this.getClientData(clients);
        this.getDepotData(depots);
        this.getVehicleData(vehicles);

        printall(depots,"depot");
        printall(vehicles,"vehicle");
        printall(clients,"client");

    }
    public<T1> void printall(Vector<T1> data, String dataType){
        System.out.println("Printing all "+dataType+"s...");
        for(T1 element:data){
            System.out.println(element.toString());
        }
    }
    public void getDepotData(Vector<Depot> depots) {

        int noIterations=5;
        for(int i=1; i<=noIterations; ++i){
            depots.add(new Depot(++noDepots));
        }
    }
    public void getVehicleData(Vector<Vehicle> vehicles){
        int depo=0; String vehModel=""; String vehLicense="";
        try {
            File vehfile= new File("./src/vehicledata.txt");
            Scanner reader = null;
            reader = new Scanner(vehfile);

            //reading format: ID Model LicensePlate
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] words = data.split(" ");

                for(String word:words)
                    if(isNumeric(word)==true){
                        depo=Integer.valueOf(word);
                    }
                    else{
                        if(vehModel.isEmpty()) {
                            vehModel=word;
                        }
                        else vehLicense=word;
                    }

                    Vehicle V=new Vehicle(depo,vehModel,vehLicense);
                    vehicles.add(V);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void getClientData(Vector<Client> clients){
        String tpClient = ""; int timeClient=0;
        try {
            File clifile= new File("./src/clientdata.txt");
            Scanner reader = null;
            reader = new Scanner(clifile);

            //reading format: ClientType TimeInterval
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] words = data.split(" ");

                for(String word:words)
                    if(isNumeric(word)==true){
                        timeClient=Integer.valueOf(word);
                    }
                    else{
                        tpClient=word;
                    }

                Client C=new Client(tpClient,noClients);
                    C.setTimeInterval(timeClient);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean isNumeric(String data){
        for(char ch:data.toCharArray())
            if(!DIGITS.contains(ch+""))
                return false;

        return true;
    }
}