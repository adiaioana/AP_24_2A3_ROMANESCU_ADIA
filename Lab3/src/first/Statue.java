package first;

public class Statue extends Attraction{
    String timetable="", name="", adress="";

    public Statue(){
        init();
    }
    public Statue(String whoseStatue, String adressOf){
        init();
        name="Statue of "+whoseStatue;
        adress=adressOf;
    }
    void init(){
        timetable="24/7";
        name="Statue of Liberty";
        adress="110 Liberty STREET";
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
    protected String getString() {
        return "Statue "+name+" from "+adress;
    }
    public boolean isVisitable(String date) {
        return true;
    }

    @Override
    public boolean isVisitableDMY(int day, int month, int year) {
        return true;
    }

    @Override
    public String getVisitingTimes() {
        return timetable;
    }
}
