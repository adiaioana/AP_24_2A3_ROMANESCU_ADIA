package first.basics;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface Visitable {
    public boolean isVisitable(String date);
    public boolean isVisitableDMYHM(int day, int month, int year, int hour, int minute);
    public Map<LocalDate, Pair<LocalTime, LocalTime>> getVisitingTimes();
    public boolean isVisitable(LocalDate localDate, LocalTime localTime);

    public default LocalTime openingHour(Map<LocalDate,Pair<LocalTime,LocalTime>> localDatePairMap) {
        LocalTime minHour=LocalTime.MAX;
        for(Pair<LocalTime,LocalTime> localTimePair:localDatePairMap.values()) {
            minHour=(minHour.isAfter(localTimePair.getKey()))?localTimePair.getKey():minHour;
        }
        return minHour;
    }
}
