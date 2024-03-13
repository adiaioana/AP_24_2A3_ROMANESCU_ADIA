
import vrp.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public final String DIGITS = "0123456789";
    public static int noClients = 0;
    public static int noDepots = 0;
    public static int noVehicles = 0;
    private int nrLines=0;
    private int nrCollumns=0;
    double[][] costDepoToClient=new double[110][110];
    double[][] costClientToClient=new double[110][110];
    String[][] mat=new String[110][110];

    public static void main(String[] args) {
        Main adia = new Main();
        //adia.compulsory();
        //adia.homework();
        adia.bonus();
    }

    public void bonus() {
        double[][] depoToCli, cliToCli;
        this.getBonusData();
        Bonus3 solver=new Bonus3(costDepoToClient,costClientToClient,noDepots,noClients,mat,nrLines,nrCollumns);
        System.out.println("The found answer is..."+ solver.solve());
    }

    public void homework() {
        Vector<Client> clients = new Vector<Client>();
        Vector<Depot> depots = new Vector<Depot>();
        Vector<AbstractVehicle> vehicles = new Vector<AbstractVehicle>();

        this.getClientData(clients);
        this.getDepotData(depots);
        this.getAbstractVehicleData(vehicles);

        for (Depot D : depots) {
            D.setVehicleid(D.getId());
        }
        int it = 0;
        for (AbstractVehicle V : vehicles) {
            it++;
            V.setSource(it);
        }

        // this.printall(vehicles,"vehicles");
        Solution solved = new Solution(clients, depots, vehicles);
        System.out.println("Best cost is..." + solved.getAnswer());
    }

    public void compulsory() {
        Vector<Client> clients = new Vector<Client>();
        Vector<Depot> depots = new Vector<Depot>();
        Vector<Vehicle> vehicles = new Vector<Vehicle>();

        this.getClientData(clients);
        this.getDepotData(depots);
        this.getVehicleData(vehicles);

        printall(depots, "depot");
        printall(vehicles, "vehicle");
        printall(clients, "client");

    }

    public <T1> void printall(Vector<T1> data, String dataType) {
        System.out.println("Printing all " + dataType + "s...");
        for (T1 element : data) {
            System.out.println(element.toString());
        }
    }

    public void getBonusData() {
        int whichDataSet = 1;


        try {
            File bonusfile = new File("./src/bonusdata.txt");
            Scanner reader = null;
            reader = new Scanner(bonusfile);
            int ln = 0;

            while (reader.hasNextLine()) {
                String data = reader.nextLine(); String[] words = data.split(" ");
                ln++;
                int cl = 0;
                for (String word : words) {
                    cl++;
                    if (word.charAt(0) == '!') {
                        whichDataSet++;
                        ln = 0;
                        break;
                    }

                    if (whichDataSet == 1) {
                        if (word.charAt(0) == 'D') {
                            mat[ln][cl]=word;
                            noDepots++;
                            if(ln>nrLines) nrLines=ln;
                            if(cl>nrCollumns) nrCollumns=cl;
                        } else if (word.charAt(0) == 'C') {
                            mat[ln][cl]=word;
                            noClients++;
                            if(ln>nrLines) nrLines=ln;
                            if(cl>nrCollumns) nrCollumns=cl;
                        }
                    } else if (whichDataSet == 2) {
                        if (isNumeric(word)) {
                            costDepoToClient[ln][cl] = Double.parseDouble(word);
                        }
                    } else {
                        if (isNumeric(word)) {
                            costClientToClient[ln][cl] = Double.parseDouble(word);
                        }
                    }
                }
            }
            reader.close();
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getDepotData(Vector<Depot> depots) {

        int noIterations = 5;
        for (int i = 1; i <= noIterations; ++i) {
            depots.add(new Depot(++noDepots));
        }
    }

    public void getVehicleData(Vector<Vehicle> vehicles) {
        int depo = 0;
        String vehModel = "";
        String vehLicense = "";
        try {
            File vehfile = new File("./src/vehicledata.txt");
            Scanner reader = null;
            reader = new Scanner(vehfile);

            //reading format: ID Model LicensePlate
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] words = data.split(" ");

                for (String word : words)
                    if (isNumeric(word) == true) {
                        depo = Integer.valueOf(word);
                    } else {
                        if (vehModel.isEmpty()) {
                            vehModel = word;
                        } else vehLicense = word;
                    }

                Vehicle V = new Vehicle(depo, vehModel, vehLicense);
                vehicles.add(V);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAbstractVehicleData(Vector<AbstractVehicle> vehicles) {
        int depo = 0;
        String vehModel = "";
        String vehLicense = "";
        String vehType = "";
        try {
            File vehfile = new File("./src/supervehicledata.txt");
            Scanner reader = null;
            reader = new Scanner(vehfile);

            //reading format: ID Model LicensePlate
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] words = data.split(" ");

                for (String word : words)

                    if (isNumeric(word) == true) {
                        depo = Integer.valueOf(word);
                    } else {
                        if (vehType.isEmpty()) {
                            vehType = word;
                        } else if (vehModel.isEmpty()) {
                            vehModel = word;
                        } else vehLicense = word;
                    }
                switch (vehType) {
                    case "Truck":
                        Truck camionul = new Truck(depo, vehModel, vehLicense);
                        vehicles.add(camionul);
                        break;
                    case "Drone":
                        Drone drona = new Drone(depo, vehModel, vehLicense);
                        vehicles.add(drona);
                        break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getClientData(Vector<Client> clients) {
        String tpClient = "";
        int timeClient = 0;
        try {
            File clifile = new File("./src/clientdata.txt");
            Scanner reader = null;
            reader = new Scanner(clifile);

            //reading format: ClientType TimeInterval
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] words = data.split(" ");

                for (String word : words)
                    if (isNumeric(word) == true) {
                        timeClient = Integer.valueOf(word);
                    } else {
                        tpClient = word;
                    }

                Client C = new Client(tpClient, noClients);
                noClients++;
                C.setTimeInterval(timeClient);
                clients.add(C);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isNumeric(String data) {
        for (char ch : data.toCharArray())
            if (!DIGITS.contains(ch + ""))
                return false;

        return true;
    }
}