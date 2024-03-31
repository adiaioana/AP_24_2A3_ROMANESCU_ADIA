package org.example.documentsystem;

public abstract class Command {
    public String commandType;
    public String[] arguments;
    public abstract void run(DocumentRepository repo) throws RepositoryException;
    public abstract String whichCommand();
}
