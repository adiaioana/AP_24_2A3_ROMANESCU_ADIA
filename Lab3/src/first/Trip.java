package first;

import first.basics.Attraction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Trip {
    String city;
    List<Attraction> lista=new ArrayList<>(110);
    public Trip(int length){
        city="Iasi";
        lista.clear();
    }
    public Trip(){
        city="Iasi";
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
            System.out.println(A.toString());
        }
    }
    public void printTimesForEachAttraction(){
        if(lista.isEmpty()){
            System.out.println("No attractions");
        }
        for(Attraction A:lista){
            System.out.println(A.toString());
            A.printMap();
        }
    }

    public void showVisitableNotPayable(){
        List<Attraction> filtered=new ArrayList<>(lista.size());
        filtered.clear();
        for(Attraction it:lista)
            if(it.isPayble()==false && it.isVisitable()==true){
                filtered.add(it);
            }
        if(filtered.isEmpty()){
            System.out.println("No attractions");
        }
        Collections.sort(filtered, new Comparator<Attraction>(){

            public int compare(Attraction o1, Attraction o2)
            {
                return o1.openingHour(o1.timetable).compareTo(o2.openingHour(o2.timetable));
            }
        });
        System.out.println("Printing filtered list of attractions(sorted by smallest opening hour):");
        for(Attraction attr:filtered){
            System.out.println(attr +" that opens at "+attr.openingHour(attr.timetable));
        }
        System.out.println();
    }

}
