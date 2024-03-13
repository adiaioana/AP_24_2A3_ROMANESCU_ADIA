package first;

public class Church extends Attraction{
    String timetable="", name="", adress="";
    public Church(){
        init();
    }
    public Church(String saintName, String adressOf){
        init();
        adress=adressOf;
        name=saintName;
    }

    public boolean isPayble() {
        return false;
    }


    public double getPrice() {
        return 0;
    }


    public boolean isVisitable(String date) {
        String[] words=timetable.split(" ,;");
        String ant="";
        for(String it: words){

            if(ant.isEmpty()==false && date.toLowerCase().equals(ant.toLowerCase())){
                if(it.toLowerCase().equals("closed")==true)
                    return false;
                return true;
            }
            ant=it;
        }
        return false;
    }

    @Override
    public boolean isVisitableDMY(int day, int month, int year) {
        return false;
    }

    @Override
    public String getVisitingTimes() {
        return timetable;
    }

    void init(){
        name="Saint Andrew Cathedral";
        adress="Street Saint Andrew 19";
        timetable= """
                Monday 13:00-17:00
                Tuesday 13:00-17:00
                Wednesday closed
                Thursday closed
                Friday closed
                Saturday 9:00-17:00
                Sunday 9:00-17:00
                """;
    }

    @Override
    protected String getString() {
        return "Church "+name+" from "+adress;
    }
}
