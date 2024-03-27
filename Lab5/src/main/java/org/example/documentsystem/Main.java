package org.example.documentsystem;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            DocumentRepository repository = new DocumentRepository("D:\\Poppy\\Lab5\\src\\main\\java\\testfolder");
            repository.displayRepositoryContent();
        } catch (RepositoryException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
