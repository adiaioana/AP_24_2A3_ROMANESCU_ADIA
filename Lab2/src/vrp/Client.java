package vrp;
public class Client {
    private ClientType type;
    private int id;
  //  String name;
    private int timeInterval;

    public Client() {
        type=ClientType.UNKNOWN;
        id=-1;
    }
    public Client(ClientType T, int no_clients){
        type=T;
        id=++no_clients;
    }
    public Client(String str, int noClients){
        if(str.equalsIgnoreCase("premium")){
            type=ClientType.PREMIUM;
        } else if (str.equalsIgnoreCase("regular")) {
            type=ClientType.REGULAR;
        }
        id=++noClients;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public ClientType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int time_interval) {
        this.timeInterval = time_interval;
    }
    /*
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }*/

    public String toString() {
        return "Client{" +
                "type=" + type +
                ", id=" + id +
               // ", name='" + name + '\'' +
                ", time_interval=" + timeInterval +
                '}';
    }
    public void printData() {
        System.out.println(toString());
    }
}
