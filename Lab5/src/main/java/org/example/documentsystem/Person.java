package org.example.documentsystem;

import java.util.List;

public record Person(String name, List<Document> documents) {
    static String personID;
    public void addDocument(Document document) {
        documents.add(document);
    }

    public void displayDocuments() {
        for (Document document : documents) {
            System.out.println(document);
        }
    }

    public String getID(){
        String[] words=name.split("_");
        for(String word:words)
            if(word.chars().anyMatch(Character::isDigit)){
                personID=word;
                return word;
            }
        return "Error at parsing for person ID";
    }
}