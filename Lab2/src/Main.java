
import vrp.*;

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
        //adia.compulsory();
        adia.homework();
    }
    public void homework(){
        Vector<Client> clients=new Vector<Client>();
        Vector<Depot> depots=new Vector<Depot>();
        Vector<AbstractVehicle> vehicles=new Vector<AbstractVehicle>();

        this.getClientData(clients);
        this.getDepotData(depots);
        this.getAbstractVehicleData(vehicles);

        for(Depot D:depots) {
            D.setVehicleid(D.getId());
        }
        int it=0;
        for(AbstractVehicle V:vehicles){
            it++;
            V.setSource(it);
        }

        this.printall(vehicles,"vehicles");
        Solution solved=new Solution(clients,depots,vehicles);
        System.out.println("Best cost is..."+solved.getAnswer());
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
    public void getAbstractVehicleData(Vector<AbstractVehicle> vehicles){
        int depo=0; String vehModel=""; String vehLicense=""; String vehType="";
        try {
            File vehfile= new File("./src/supervehicledata.txt");
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
                        if(vehType.isEmpty()){
                            vehType=word;
                        }
                        else if(vehModel.isEmpty()) {
                            vehModel=word;
                        }
                        else vehLicense=word;
                    }
                switch (vehType){
                    case "Truck": Truck camionul=new Truck(depo,vehModel,vehLicense); vehicles.add(camionul); break;
                    case "Drone": Drone drona=new Drone(depo,vehModel,vehLicense); vehicles.add(drona); break;
                }
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
                    noClients++;
                    C.setTimeInterval(timeClient);
                    clients.add(C);
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