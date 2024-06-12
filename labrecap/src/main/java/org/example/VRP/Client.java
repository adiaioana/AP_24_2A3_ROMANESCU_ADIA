package org.example.VRP;

import java.util.Random;

public class Client {
    private ClientType type;
    private Random rand = new Random();
    int id;
    private String name;
    private TimeInterval timeInterval;
    public Client(String cName, int ind){
        name=cName;
        id=ind;
        type=(rand.nextInt(2)==0)?ClientType.REGULAR:ClientType.PREMIUM;
        timeInterval=new TimeInterval();
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
