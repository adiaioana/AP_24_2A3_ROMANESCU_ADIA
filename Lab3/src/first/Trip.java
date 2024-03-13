package first;

import java.util.ArrayList;
public class Trip {
    String city, date,day;
    ArrayList<Attraction> lista=new ArrayList<>(110);
    public Trip(){
        city="Iasi";
        date="13-03-2024";
        day="Wednesday";
        lista.clear();
    }
    public void addAttraction(Attraction A){
        lista.add(A);
    }
    public void printAttractions(){
        if(lista.isEmpty()){
            System.out.println("No attractions");
        }
        for(Attraction A:lista){
            System.out.println(A.getString());
        }
    }

}
