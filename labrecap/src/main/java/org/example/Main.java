package org.example;

import org.example.VRP.Depot;
import org.example.VRP.*;
import org.example.collectionsLab.Driver;
import org.example.collectionsLab.Passenger;
import org.example.collectionsLab.Person;
import org.example.ex12.P;
import org.example.ex13.MapSolver;

import java.util.*;


public class Main {
    static Random random=new Random();
    static final String[] locations={"Gara","CUG","Piata Alexandru", "Piata Unirii","Cantemir","Pantelimon"};
    static final String[] clientNames={"Petru","Alex","Ioana"};

    public static void main(String[] args) {
        var Adia=new Main();
        //Adia.vrp_problem();
       // Adia.carSharingProblem();
        Adia.model2023();
    }
    public void model2023()
    {
        Set<double[][]> ex3=new HashSet<>();
        List<String> values=new LinkedList<String>();
        values.add("opa"); values.add("nia"); values.add("ano");
        var sum=values.stream().mapToInt(e -> e.length()).sum();
        //System.out.println(sum);
        var result=frecvChInString(values);
        //System.out.println(result.toString());

        var pol=new P(1,2);
        //System.out.println(pol.getPolValue(4)+" "+pol.getPolValue(9));
        int nrThreads=3;
        boolean[][] a=new boolean[4][4];
        a[0][1]=a[0][3]=true;
        a[1][3]=a[1][2]=a[1][0]=true;
        a[2][3]=true;
        a[3][0]=a[3][2]=a[3][1]=true;

        var solver=new MapSolver(nrThreads,a,4);
        solver.begin();
    }
    public Map<Character,List<String>> frecvChInString(List<String> stringList)
    {
        Map<Character,List<String>> frCh=new HashMap<Character,List<String>>();
        for(var string:stringList)
            for(int index=0; index<string.length();index++)
            {
                var ch=string.charAt(index);
                if(frCh.containsKey(ch)){
                    var list=frCh.get(ch);
                    list.add(string);
                    frCh.remove(ch);
                    frCh.put(ch,list);
                }
                else {
                    List<String> list=new ArrayList<>();
                    list.add(string);
                    frCh.put(ch,list);
                }
            }
        return frCh;
    }

    public <T> void print(List<T> list,String printMessage)
    {
        var ind=0;
        System.out.println(printMessage);
        for(var x:list){
            ind++;
            System.out.format("[%d] %s\n",ind,x.toString());
        }
        System.out.println();
    }
    public void carSharingProblem(){
        List<Person> personList=new LinkedList<>();
        for(var loc:locations){
            personList.add((random.nextInt(2)==0?(new Passenger(loc)):(new Driver(loc))));
        }
        print(personList,"printing persons's list");
        var passengerList=personList.stream().filter(e-> e.isPassenger()).toList();
        print(passengerList, "printing passenger's list");
        var driverList=personList.stream().filter(e -> e.isDriver()).toList();
        print(driverList, "printing driver's list");
    }
    public static void vrp_problem(){
        Vector<Depot> depots = new Vector<>(); var ind=0;
        Vector<Vehicle> vehicles=new Vector<>();
        Vector<Client> clients=new Vector<>();

        for(var dName: locations){
            depots.add(new Depot(++ind,dName));
        }
        ind=0;
        for(var cName:clientNames){
            clients.add(new Client(cName,++ind));
        }

        Scanner in = new Scanner(System.in);
        int noVehicles=in.nextInt();

        for(var iV=0; iV<noVehicles; iV++){
            if(iV%2==0)
                vehicles.add(new Truck(iV+1, depots.get(random.nextInt(depots.size()))));
            else
                vehicles.add(new Drone(iV+1,depots.get(random.nextInt(depots.size()))));
        }

    }
}