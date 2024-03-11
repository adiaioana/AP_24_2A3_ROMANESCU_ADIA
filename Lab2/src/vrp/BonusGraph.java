package vrp;

import java.lang.module.Configuration;
import java.util.Vector;
import java.util.Collections;

public class BonusGraph {
    public final double MAXTRAVEL = 100000;
    public final int MAXSHUFFLES = 100;
    public int noShuffles = 0;

    private int nrClients;
    private int nrDepots;
    double[][] travelDepoToClient;
    double[][] travelClientToClient;

    private int viz[] = new int[nrClients + 10];
    private int lastAdded[] = new int[nrDepots + 10];
    private int totalCost[] = new int[nrDepots + 10];
    private int notBack[] = new int[nrDepots + 10];
    private Vector<Integer> config = new Vector<Integer>(nrDepots + 10);

    public BonusGraph() {
        nrClients = nrDepots = 0;
        noShuffles = 0;
        travelClientToClient = new double[110][110];
        travelDepoToClient = new double[110][110];
        init();
    }

    public BonusGraph(double[][] costDC, double[][] costCC, int nrD, int nrC) {
        nrClients = nrC;
        nrDepots = nrD;
        noShuffles = 0;
        travelClientToClient = costCC;
        travelDepoToClient = costDC;
        init();
    }

    public double greedy_algorithm() {
        double finalAnswer = 0;
        for (int iD : config) {
            int idc = -1;
            double mntravel = MAXTRAVEL;
            for (int iC = 1; iC <= nrClients; iC++)
                if (viz[iC] == 0 && mntravel > travelDepoToClient[iD][iC] && travelDepoToClient[iD][iC] > 0) {
                    mntravel = travelDepoToClient[iD][iC];
                    idc = iC;
                }
            if (idc == -1) {
                init();
                Collections.shuffle(config);
                noShuffles++;
                if (noShuffles < MAXSHUFFLES)
                    return greedy_algorithm();
                else return -1;
            }
            viz[idc] = iD;
            totalCost[iD] += mntravel;
            lastAdded[iD] = idc;
            finalAnswer += mntravel;
        }
        boolean ok;
        int no_backs = nrDepots;
        int no_iterations = 0;
        do {
            ok = false;
            no_iterations++;
            for (int i : config)
                if (notBack[i] == 0) {
                    ok = true;
                    int idc = -1;
                    double mntravel = MAXTRAVEL;
                    for (int iC = 1; iC <= nrClients; ++iC)
                        if (viz[iC] == 0 && mntravel > travelClientToClient[lastAdded[i]][iC] && travelClientToClient[lastAdded[i]][iC] > 0) {
                            mntravel = travelClientToClient[lastAdded[i]][iC];
                            idc = iC;
                        }
                    if (idc == -1 && no_backs > 1) {
                        init();
                        Collections.shuffle(config);;
                        noShuffles++;
                        if (noShuffles < MAXSHUFFLES)
                            return greedy_algorithm();
                        else return -1;
                    }
                    viz[idc] = i;
                    lastAdded[i] = idc;
                    totalCost[i] += mntravel;
                    finalAnswer += mntravel;
                    if (travelDepoToClient[i][idc] > 0) {
                        notBack[i] = 1;
                        totalCost[i] += travelDepoToClient[i][idc];
                        finalAnswer += travelDepoToClient[i][idc];
                        no_backs--;
                    }

                    break;
                }

        } while (ok == true || no_iterations > 1000000);

        return finalAnswer;

    }

    public void init() {
        for (int i = 0; i <= nrClients; ++i)
            viz[i] = 0;
        for (int i = 0; i <= nrDepots; ++i) {
            totalCost[i] = 0;
            notBack[i] = 0;
            lastAdded[i] = 0;
            if (i > 0)
                config.add(i);
        }
    }

    public void setTravelClientToClient(double[][] travelClientToClient) {
        this.travelClientToClient = travelClientToClient;
    }

    public void setTravelDepoToClient(double[][] travelDepoToClient) {
        this.travelDepoToClient = travelDepoToClient;
    }

    public double[][] getTravelDepoToClient() {
        return travelDepoToClient;
    }

    public double[][] getTravelClientToClient() {
        return travelClientToClient;
    }

    public int getNrClients() {
        return nrClients;
    }

    public int getNrDepots() {
        return nrDepots;
    }

    public void setNrClients(int nrClients) {
        this.nrClients = nrClients;
    }

    public void setNrDepots(int nrDepots) {
        this.nrDepots = nrDepots;
    }
}
