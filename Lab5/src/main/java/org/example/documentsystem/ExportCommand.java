package org.example.documentsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ExportCommand extends Command {
    public ExportCommand(String input) throws RepositoryException {
        String[] parts = input.split("\\s+");
        arguments = Arrays.stream(parts).filter(s -> s != null && !s.isEmpty()).toArray(String[]::new);
        if (arguments.length != 2)
            throw new RepositoryException("Invalid arguments for export command");
    }

    public ExportCommand(String[] args) {
        arguments = args;
    }

    @Override
    public void run(DocumentRepository repo) throws RepositoryException {
        // Export repository to an external file using JSON format (e.g., using Jackson library)
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(arguments[0]), repo);
        } catch (IOException e) {
            throw new RepositoryException("Error exporting repository: " + e.getMessage());
        }
    }

    @Override
    public String whichCommand() {
        return "export";
    }
}
