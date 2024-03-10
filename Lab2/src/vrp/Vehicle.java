package vrp;
public class Vehicle implements Object {
    private Depot source;
    private String model;
    private String licensePlate;
    public Vehicle() {
        model=""; licensePlate=""; source=new Depot();
    }
    public Vehicle(int depo, String strModel, String strLicense){
        source=new Depot(depo);
        model=strModel;
        licensePlate=strLicense;
    }

    public Depot getSource() {
        return source;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSource(Depot source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "source=" + source +
                ", model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
    @Override
    public void printData() {
        System.out.println(toString());
    }
    @Override
    public boolean equals(Object B) {
        return this.toString().equals(B.toString());
    }
}
