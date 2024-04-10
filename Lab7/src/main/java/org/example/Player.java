package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player extends Thread {
    private String name;
    private Bag bag;
    public volatile boolean running = true;
    private List<Tile> tiles = new ArrayList<>();
    public Player(String name, Bag bagOf) { this.name = name; bag=bagOf;}

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        Random rand=new Random();
        while (running) {
            if(bag.extractTiles(name,rand.nextInt(3)+1).isEmpty())
                break;
        }
    }
}
