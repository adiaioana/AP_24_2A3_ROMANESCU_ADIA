package first.basics;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public abstract class Attraction implements Visitable, Payable {
    public String name = "", location = "", strtimes = "";
    public Map<LocalDate, Pair<LocalTime, LocalTime>> timetable;


    public void makeMap() {
        this.timetable = HashMap.newHashMap(110);
        LocalDate curr = null;
        Pair<LocalTime, LocalTime> window = null;
        for (String line : strtimes.split("\n"))
            for (String word : line.split(" ")) {
                if (word.contains(":")) {
                    int i1 = 0, i2 = 0;
                    window = new Pair<>(LocalTime.now(), LocalTime.now());

                    for (String timeNr : word.split("-")) {
                        if (i1 == 0) {
                            i1 = 1;
                            window.setKey(LocalTime.parse(timeNr));
                        } else if (i2 == 0) {
                            i2 = 1;
                            window.setValue(LocalTime.parse(timeNr));
                        }
                    }

                    //System.out.println( );
                    this.timetable.put(curr, window);
                    curr = null;
                } else if (word.length()>3 && word.contains("-") == true) {
                    //System.out.println(word);
                    curr = LocalDate.parse(word);
                } else if (word.toLowerCase() == "closed") {
                    window = new Pair(LocalTime.now(), LocalTime.now());
                    this.timetable.put(curr, window);
                    curr = null;
                }
            }
    }
    public void printMap(){
        for(LocalDate key:timetable.keySet()){
            System.out.println("on "+key+" the window is "+timetable.get(key));
        }
    }
    public boolean isVisitable(LocalDate localDate, LocalTime localTime) {
        if (timetable.containsKey(localDate) == true) {
            Pair<LocalTime, LocalTime> window = timetable.get(localDate);
            if (localTime.isAfter(window.getKey()) && localTime.isBefore(window.getValue()))
                return true;
        }
        return false;
    }
    public abstract int color();
    public boolean isVisitable(String date) {
        LocalTime localTime = null;
        LocalDate localDate = null;
        for (String word : date.split(" ")) {
            if (word.contains("/") == true) {
                localDate = LocalDate.parse(word);
            } else if (word.contains(":") == true) {
                localTime = LocalTime.parse(word);
            }
        }
        return isVisitable(localDate, localTime);
    }

    public abstract boolean isVisitable();
    public boolean isVisitable(LocalDate localDate) {
        return (timetable.containsKey(localDate));
    }
    public abstract String toString();
    public abstract int getInd();

    public boolean isVisitableDMYHM(int day, int month, int year, int hour, int minute) {
        LocalDate localDate = LocalDate.of(year, month, day);
        LocalTime localTime = LocalTime.of(hour, minute);
        return isVisitable(localDate, localTime);
    }

}
