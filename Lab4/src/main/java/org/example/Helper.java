package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Helper {
    final String destinationsFile="./src/main/java/org/example/destinations.txt";
    HashMap<Integer, String> addresses=new HashMap<Integer, String>(110);
    public Helper(){
        //setAddresses();
    }
    public void debug(){
        for(int a:this.addresses.keySet()){
            System.out.println("K="+a+"; V="+addresses.get(a)+";");
        }
    }
    public void addAdress(String addr){
        String onlyStreet="";
        String[] seqs=addr.split(" ");
        for(String word:seqs)
            if(!word.chars().anyMatch(Character::isDigit)){
                onlyStreet+=word+" ";
            }
        if(onlyStreet.length()==0)
            return;
        onlyStreet=onlyStreet.substring(0,onlyStreet.length()-1);

        if(this.addresses.containsValue(onlyStreet))
            return;
        addresses.put(addresses.size()+1,onlyStreet);
    }
    public void setAddresses(){
        try {
            File myObj = new File(destinationsFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] hihi=data.split(" ");
                int nr=0; String addr="";
                for(String word:hihi)
                    if(word.chars().anyMatch(Character::isDigit)){
                        nr=Integer.valueOf(word);
                        addr=addr.substring(0,addr.length()-1);
                        if(!addresses.containsKey(nr) && !addresses.containsValue(addr))
                            addresses.put(nr,addr);
                        addr="";
                    }
                    else{
                        addr+=word+" ";
                    }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public int whichId(String addr)
    {
        String onlyStreet="";
        String[] seqs=addr.split(" ");
        for(String word:seqs)
            if(!word.chars().anyMatch(Character::isDigit)){
                onlyStreet+=word+" ";
            }
        if(onlyStreet.length()==0)
            return 0;
        onlyStreet=onlyStreet.substring(0,onlyStreet.length()-1);

        if(!this.addresses.containsValue(onlyStreet))
            return 0;

        for(int i: addresses.keySet())
            if(addresses.get(i).equals(onlyStreet))
                return i;
        return 0;
    }
    public String whichAddr(int K){
        if(addresses.containsKey(K))
            return addresses.get(K);
        return "Wrong id";
    }
}
