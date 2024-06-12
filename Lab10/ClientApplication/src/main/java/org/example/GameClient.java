package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient extends Thread {
    private String serverAddress;
    private int port;

    public GameClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    @Override
    public void run() {
        try (
                Socket socket = new Socket(serverAddress, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            System.out.print("[Enter command:]");
            while ((userInput = in.readLine()) != null) {
                out.println(userInput);
                if (userInput.equals("exit")) {
                    break;
                }
                System.out.print("[Enter command:]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}