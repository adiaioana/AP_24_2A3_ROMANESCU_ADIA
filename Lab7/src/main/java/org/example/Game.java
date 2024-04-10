package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final Bag bag = new Bag(10);
    private final List<Player> players = new ArrayList<>();
    public void addPlayer(Player player) {
        players.add(player);
    }
    public void play() {

        for (Player player : players) {
            Thread playerThread=new Thread(player);
            playerThread.start();
            if(bag.tilesIsEmpty())
                player.running=false;
        }
    }
}
