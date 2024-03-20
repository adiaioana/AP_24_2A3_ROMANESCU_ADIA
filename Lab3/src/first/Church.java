package first;

import first.basics.Attraction;
import first.basics.Pair;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

public class Church extends Attraction {

    public Church(){
        init();
    }
    public Church(String saintName, String locationOf){
        init();
        location=locationOf;
        name=saintName;
    }
    public boolean isPayble() {
        return false;
    }
    public double getPrice() {
        return 0;
    }

    @Override
    public int getInd() {
        return 0;
    }

    @Override
    public Map<LocalDate, Pair<LocalTime, LocalTime>> getVisitingTimes() {
        return timetable;
    }
    void init(){
        name="Saint Andrew Cathedral";
        location="Street Saint Andrew 19";
        strtimes= """
                2024-03-16 09:00-17:00
                2024-03-17 09:00-19:00
                2024-03-18 CLOSED
                """;
        makeMap();
    }

    @Override
    public int color() {
        return 1;
    }

    @Override
    public String toString() {
        return "Church "+name+" from "+location;
    }



    @Override
    public boolean isVisitable() {
        return true;
    }
}
