package org.example;

import java.net.ConnectException;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {
        var adia=new Main();
        adia.compulsory();
    }

    public void compulsory(){
        String serverAddress = "localhost";
        int port = 12500;
        GameClient client = new GameClient(serverAddress, port);
        client.start();
    }
    public void homework(){

    }
}