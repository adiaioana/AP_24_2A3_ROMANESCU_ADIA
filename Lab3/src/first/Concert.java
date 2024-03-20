package first;

import first.basics.Attraction;
import first.basics.Pair;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Concert extends Attraction {
    int ticketPrice;
    String artist;
    public Concert(){
        init();
    }
    public Concert(String who){
        init();
        artist=who;
        name="Concert with "+artist;
    }

    private void init(){
        ticketPrice=100;
        artist="Bosquito";
        strtimes= """
                2024-03-16 20:00-22:00
                2024-03-18 12:29-15:00
                """;
        makeMap();
    }

    @Override
    public int getInd() {
        return 1;
    }

    @Override
    public int color() {
        return 2;
    }

    @Override
    public String toString() {
        return "Concert with "+artist+" with price "+ticketPrice;
    }
    public boolean isPayble() {
        return true;
    }
    public double getPrice() {
        return ticketPrice;
    }
    public boolean isVisitable() {
        return false;
    }
    @Override
    public Map<LocalDate, Pair<LocalTime, LocalTime>> getVisitingTimes() {
        return timetable;
    }
}
