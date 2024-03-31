package org.example.documentsystem;

import javax.swing.text.View;
import java.io.File;
import java.awt.Desktop;
import java.util.Arrays;

public class ViewCommand extends Command{

    public ViewCommand(String input) throws RepositoryException {

        String[] parts= input.split("\\s+");
        arguments= Arrays.stream(parts).filter(s -> s!=null && !s.isEmpty()).toArray(String[]::new);
        if(arguments.length>2)
            throw new RepositoryException("Too many arguments for view command");
    }
    public ViewCommand(String[] args){
        arguments=args;
    }
    @Override
    public void run(DocumentRepository repo) throws RepositoryException {
        System.out.println(repo.getMasterDirectory()+ "\\"+arguments[0]);
        try{
            File fileThatOpens=new File(repo.getMasterDirectory()+ arguments[0]);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(fileThatOpens);
        }
        catch(Exception NullPointerException){
            throw new RepositoryException("Error: "+ "File pointer null");
        }
    }

    @Override
    public String whichCommand() {
        return arguments[0];
    }
}
