package first;

public class Concert extends Attraction{
    int ticketPrice;
    String artist, date;
    public Concert(){
        init();
    }
    public Concert(String who, String when){
        init();
        artist=who;
        date=when;
    }

    private void init(){
        ticketPrice=100;
        artist="Bosquito";
        date="24/7/2025";
    }
    @Override
    protected String getString() {
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
    public boolean isVisitable(String date) {
        return false;
    }

    @Override
    public boolean isVisitableDMY(int day, int month, int year) {
        return false;
    }

    @Override
    public String getVisitingTimes() {
        return "None";
    }
}
