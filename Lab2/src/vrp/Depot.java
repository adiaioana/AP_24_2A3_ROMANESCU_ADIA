package vrp;

public class Depot implements Object {
    private int id;
    private int vehicleid;

    public Depot(int nr){
        id=nr;vehicleid=-1;
    }
    public Depot(){
        id=0;vehicleid=-1;
    }

    public int getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(int vehicleid) {
        this.vehicleid = vehicleid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "id=" + id +
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
