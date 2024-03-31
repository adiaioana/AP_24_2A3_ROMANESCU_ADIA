package org.example.documentsystem;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportCommand extends Command {
    public ReportCommand(String input) throws RepositoryException {
        String[] parts = input.split("\\s+");
        arguments = Arrays.stream(parts).filter(s -> s != null && !s.isEmpty()).toArray(String[]::new);
        if (arguments.length != 1)
            throw new RepositoryException("Invalid arguments for report command");
    }

    public ReportCommand(String[] args) {
        arguments = args;
    }

    @Override
    public void run(DocumentRepository repo) throws RepositoryException {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        Template template = velocityEngine.getTemplate(repo.getMasterDirectory()+"report_template.vm");

        VelocityContext context = new VelocityContext();
        List<String> documents = new ArrayList<>();

        for (Document doc : repo.getDocuments(arguments[0])) {
            documents.add(doc.getName());
        }
        context.put("documents", documents);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        System.out.println(writer.toString());
    }


    @Override
    public String whichCommand() {
        return "report";
    }
}

