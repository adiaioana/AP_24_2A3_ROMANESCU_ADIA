package org.example.VRP;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class TimeInterval {
    private LocalDateTime start, end;
    private Random rand = new Random();
    public TimeInterval(){
        var dur=rand.nextInt(10);
        start=LocalDateTime.now();
        end=start.plusHours(dur);
    }
    public long getDurationSeconds()
    {
        return Duration.between(start,end).toSeconds();
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
