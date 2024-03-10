package vrp;

public class Truck extends AbstractVehicle{
    private double capacity;
    public Truck(int depo, String strModel, String strLicense){
        source=depo;
        model=strModel;
        licensePlate=strLicense;
    }
    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}
