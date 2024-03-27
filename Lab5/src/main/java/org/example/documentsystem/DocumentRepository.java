package org.example.documentsystem;

import javax.print.Doc;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DocumentRepository {
    private final String masterDirectory;
    Vector<Person> people=new Vector<>();

    public DocumentRepository(String masterDirectory) {
        this.masterDirectory = masterDirectory;
    }

    public void displayRepositoryContent() throws RepositoryException {
        File directory = new File(masterDirectory);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new RepositoryException("Master directory does not exist or is not a directory");
        }

        File[] personDirectories = directory.listFiles(File::isDirectory);
        if (personDirectories == null) {
            throw new RepositoryException("No person directories found in the master directory");
        }

        for (File personDirectory : personDirectories) {

            File[] personalFiles = personDirectory.listFiles(File::isDirectory);
            List<Document> docs = new ArrayList<>();
            for(File eachFile:personalFiles)
                docs.add(new Document(eachFile.getName()));

            people.add(new Person(personDirectory.getName(),docs));
            System.out.println("Person ID: " + people.elementAt(people.size()-1).getID());
        }
    }
}