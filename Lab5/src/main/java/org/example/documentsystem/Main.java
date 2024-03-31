package org.example.documentsystem;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    static DocumentRepository repository = new DocumentRepository("D:\\Poppy\\Lab5\\src\\main\\java\\testfolder");

    public static void main(String[] args) throws RepositoryException {
        var adia=new Main();
        adia.homework();
    }
    public void compulsory(){
        try {
            repository.displayRepositoryContent();
        } catch (RepositoryException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public void homework() throws RepositoryException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Shell> ");

            String commandLine = scanner.nextLine();

            String[] parts = commandLine.split("\\s+");
            String command = parts[0];
            String[] arguments = new String[parts.length - 1];
            System.arraycopy(parts, 1, arguments, 0, arguments.length);

            handleCommand(command, arguments);
        }
    }
        private static void handleCommand(String command, String[] arguments) throws RepositoryException {
            // Example: Echo command
            if (command.equalsIgnoreCase("echo")) {
                for (String arg : arguments) {
                    System.out.print(arg + " ");
                }
                System.out.println();
            }
            switch (command.toLowerCase()){
                case "view":
                {
                    var newComm= new ViewCommand(arguments);
                    newComm.run(repository);
                    break;
                }
                case "report": {
                    var newComm= new ReportCommand(arguments);
                    newComm.run(repository);
                    break;
                }
                case "export": {
                    var newComm= new ReportCommand(arguments);
                    newComm.run(repository);
                    break;
                }
                default:{
                    System.out.println("Command not recognized: " + command);
                    break;
                }
            }
        }
}
