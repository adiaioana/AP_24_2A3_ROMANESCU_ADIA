package vrp;
public abstract class AbstractVehicle implements Object {
    protected int source;
    protected String model;
    protected Integer vehId;
    protected String licensePlate;
    public AbstractVehicle() {
        model=""; licensePlate=""; source=0;
    }
    public AbstractVehicle(int depo, String strModel, String strLicense){
        source=depo;
        model=strModel;
        licensePlate=strLicense;
    }

    public Integer getVehId(){
        return vehId;
    }

    public void setVehId(Integer vehId) {
        this.vehId = vehId;
    }

    public int getSource() {
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

    public void setSource(int source) {
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
