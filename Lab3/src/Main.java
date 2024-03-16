import first.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Main adia = new Main();
        adia.homework();
    }

    public void howTimeWorks() {
        LocalDate data = LocalDate.now();
        LocalTime timp = LocalTime.now();
        String str = String.valueOf(timp);
        System.out.println(str);
    }

    public void compulsory() {
        Trip firstTrip = new Trip();
        Church saintPeter = new Church("Peter", "11 Peter ST");
        Statue vidraStatue = new Statue("Vidra", "CCS");
        Concert farazahar = new Concert("Fara Zahar");

        firstTrip.addAttraction(saintPeter);
        firstTrip.addAttraction(vidraStatue);
        firstTrip.addAttraction(farazahar);

        firstTrip.printAttractions();
    }

    public void homework() {
        Trip firstTrip = new Trip();
        Church saintPeter = new Church("Peter", "11 Peter ST");
        Statue vidraStatue = new Statue("Vidra", "CCS");
        Concert farazahar = new Concert("Fara Zahar");

        firstTrip.addAttraction(saintPeter);
        firstTrip.addAttraction(vidraStatue);
        firstTrip.addAttraction(farazahar);
        firstTrip.showVisitableNotPayable();
        TravelPlan travelPlan=new TravelPlan(10,firstTrip);
        travelPlan.makeTravelPlan();
    }

    public void bonus() {

    }

}