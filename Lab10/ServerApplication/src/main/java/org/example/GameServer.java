package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread {
    private int port;
    private int noClients=0;
    private ServerSocket serverSocket;

    public GameServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(clientSocket, ++noClients);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}