import first.*;
public class Main {
    public static void main(String[] args) {
        Main adia = new Main();
        adia.compulsory();
    }
    public void compulsory(){
        Trip firstTrip=new Trip();
        Church saintPeter=new Church("Peter","11 Peter ST");
        Statue vidraStatue=new Statue("Vidra","CCS");
        Concert farazahar=new Concert("Fara Zahar", "14/03/2025");
        firstTrip.addAttraction(saintPeter);
        firstTrip.addAttraction(vidraStatue);
        firstTrip.addAttraction(farazahar);
        firstTrip.printAttractions();
    }

}