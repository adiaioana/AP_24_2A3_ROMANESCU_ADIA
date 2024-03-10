package vrp;
import java.util.Random;

public class Solution {
    Problem problem;
    /**
     * Assume that the times required to travel between the locations
     * (depot-to-client or client-to-client) are known (You may generate them randomly).
     * Implement a simple greedy algorithm for allocating clients to vehicles.
    **/
    double [][] travelDepoToClient;
    double [][] travelClientToClient;
    public Solution(){
        travelClientToClient=new double[110][110];
        travelDepoToClient=new double[110][110];
        problem=new Problem();
        this.initializeTravelTime();
    }
    public Solution(Problem p){
        problem=p;
        int cMax=p.nrClients()+10; int dMax=p.nrDepots()+10;
        travelClientToClient=new double[cMax][cMax];
        travelDepoToClient=new double[dMax][cMax];
        this.initializeTravelTime();
    }

    public void initializeTravelTime(){
        Random rand = new Random();
        for(int it1=1; it1<= problem.nrClients(); it1++)
            for(int it2=1; it2<= problem.nrClients(); it2++)
                travelClientToClient[it1][it2]=((int)rand.nextInt()%100)+((double) rand.nextInt()/100);

        for(int it1=1; it1<= problem.nrDepots(); it1++)
            for(int it2=1; it2<= problem.nrClients(); it2++)
                travelDepoToClient[it1][it2]=((int)rand.nextInt()%100)+((double) rand.nextInt()/100);
    }

    public double[][] getTravelClientToClient() {
        return travelClientToClient;
    }

    public double[][] getTravelDepoToClient() {
        return travelDepoToClient;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public void setTravelDepoToClient(double[][] travelDepoToClient) {
        this.travelDepoToClient = travelDepoToClient;
    }

    public void setTravelClientToClient(double[][] travelClientToClient) {
        this.travelClientToClient = travelClientToClient;
    }
}
