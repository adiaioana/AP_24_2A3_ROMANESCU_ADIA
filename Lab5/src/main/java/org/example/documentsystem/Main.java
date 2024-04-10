package org.example.documentsystem;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static DocumentRepository repository = new DocumentRepository("D:\\Poppy\\Lab5\\src\\main\\java\\testfolder");

    public static void main(String[] args) throws RepositoryException, IOException, InvalidFormatException {
        var adia=new Main();
        adia.bonus();
    }
    public void compulsory(){
        try {
            repository.displayRepositoryContent();
        } catch (RepositoryException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public void bonus() throws IOException, InvalidFormatException {
        try {
            var bonus = new Bonus("D:\\Poppy\\Lab5\\src\\main\\java\\testfolder\\employee_abilities.xlsx");
            List<Set<String>> maximalGroups = bonus.findMaximalGroups();
            int groupNumber = 1;
            for (Set<String> group : maximalGroups) {
                System.out.println("Group " + groupNumber + ": " + group);
                groupNumber++;
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
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
                    var newComm= new ExportCommand(arguments);
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
