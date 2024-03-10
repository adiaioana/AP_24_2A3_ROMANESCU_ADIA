package vrp;
import java.util.Random;
import java.util.Vector;

public class Solution {
    public final double MAXTRAVEL=100000;
    Problem problem;
    double answer;
    /**
     * Assume that the times required to travel between the locations
     * (depot-to-client or client-to-client) are known (You may generate them randomly).
     * Implement a simple greedy algorithm for allocating clients to vehicles.
    **/
    double [][] travelDepoToClient;
    double [][] travelClientToClient;
    public Solution(Vector<Client> clis, Vector<Depot> depos, Vector<AbstractVehicle> vehs){
        answer=0;
        travelClientToClient=new double[110][110];
        travelDepoToClient=new double[110][110];
        problem=new Problem(clis,depos,vehs);
        this.initializeTravelTime();
        answer=this.solveGreedy();
    }
    public Solution(Problem p){
        answer=0;
        problem=p;
        int cMax=p.nrClients()+10; int dMax=p.nrDepots()+10;
        travelClientToClient=new double[cMax][cMax];
        travelDepoToClient=new double[dMax][cMax];
        this.initializeTravelTime();
        answer=this.solveGreedy();
    }

    public void initializeTravelTime(){
        Random rand = new Random();
        for(int it1=1; it1<= problem.nrClients(); it1++)
            for(int it2=1; it2<= problem.nrClients(); it2++)
                travelClientToClient[it1][it2]=((int)rand.nextInt()%100);

        for(int it1=1; it1<= problem.nrDepots(); it1++)
            for(int it2=1; it2<= problem.nrClients(); it2++)
                travelDepoToClient[it1][it2]=((int)rand.nextInt()%100);
    }

    public double solveGreedy(){
        int viz[]=new int[problem.nrClients()+10];
        int lastAdded[]=new int[problem.nrDepots()+10];
        int totalCost[]=new int[problem.nrDepots()+10];

        for(int i=0; i<= problem.nrClients(); ++i)
            viz[i]=0;
        for(int i=0; i<=problem.nrDepots(); ++i){
            totalCost[i]=0;
            lastAdded[i]=0;
        }

        for(Depot D: problem.depots){
            int idc=-1;
            double mntravel=MAXTRAVEL;
            for(Client C: problem.clients)
                if(viz[C.getId()]==0 && mntravel>travelDepoToClient[D.getId()][C.getId()]){
                    mntravel=travelDepoToClient[D.getId()][C.getId()];
                    idc=C.getId();
                }
            if(idc==-1)
                continue;
            viz[idc]=D.getId();
            totalCost[D.getId()]+=mntravel;
            lastAdded[D.getId()]=idc;
        }
        boolean ok;
        int no_iterations=0;
        do{
            ok=false;
            no_iterations++;
            for(int i=1; i<= problem.nrClients(); ++i)
                if(viz[i]!=0) {
                    int idc=-1;
                    double mntravel=MAXTRAVEL;
                    for(Client C: problem.clients)
                        if(viz[C.getId()]==0 && mntravel>travelClientToClient[i][C.getId()]){
                            mntravel=travelClientToClient[i][C.getId()];
                            idc=C.getId();
                        }
                    if(idc==-1)
                        continue;
                    viz[idc]=viz[i];
                    lastAdded[viz[i]]=idc;
                    totalCost[viz[i]]+=mntravel;
                }
                else ok=true;

        }while(ok==true || no_iterations>1000000);

        double finalAnswer=0;
        for(Depot D: problem.getDepots()){
            totalCost[D.getId()]+=travelDepoToClient[D.getId()][lastAdded[D.getId()]];
            finalAnswer+=totalCost[D.getId()];
        }
        return finalAnswer;
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

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }
}
