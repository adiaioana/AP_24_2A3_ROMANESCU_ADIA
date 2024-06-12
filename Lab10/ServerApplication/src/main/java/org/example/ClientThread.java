package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private int clientID;
    private BufferedReader in;

    public ClientThread(Socket clientSocket, int cliID) {
        this.clientSocket = clientSocket;
        this.clientID=cliID;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                System.out.println("Client ["+clientID+"] sent to server: "+inputLine);
                if (inputLine.contains("stop")) {
                    System.out.println("Server stopped");
                    break;
                } else {
                    out.println("Server received the request: " + inputLine);
                }
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}