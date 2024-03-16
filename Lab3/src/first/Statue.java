package first;

import first.basics.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Statue extends Attraction {
    public Statue(){
        init();
    }
    public Statue(String whoseStatue, String locationOf){
        init();
        name="Statue of "+whoseStatue;
        location=locationOf;
    }
    void init(){
        name="Statue of Liberty";
        location="110 Liberty STREET";
        strtimes= """
                2024-03-17 10:30-20:30
                2024-03-18 10:03-13:00
                """;
        makeMap();
    }
    @Override
    public boolean isPayble() {
        return false;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    public boolean isVisitable() {
        return true;
    }
    @Override
    public String toString() {
        return "Statue "+name+" from "+location;
    }
    @Override
    public Map<LocalDate, Pair<LocalTime, LocalTime>> getVisitingTimes() {
        return timetable;
    }
}
