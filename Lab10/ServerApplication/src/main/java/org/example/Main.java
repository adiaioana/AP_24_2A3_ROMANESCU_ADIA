package org.example;

public class Main {
    public static void main(String[] args) {
        var adia= new Main();
        adia.compulsory();
    }

    public void compulsory(){
        int port=12500;
        var gs=new GameServer(port);
        gs.start();
    }
}