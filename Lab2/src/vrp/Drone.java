package vrp;

public class Drone extends AbstractVehicle{
    private double maxFlightDur;
    public Drone(int depo, String strModel, String strLicense){
        source=depo;
        model=strModel;
        licensePlate=strLicense;
    }

    public double getMaxFlightDur() {
        return maxFlightDur;
    }

    public void setMaxFlightDur(double maxFlightDur) {
        this.maxFlightDur = maxFlightDur;
    }
}
