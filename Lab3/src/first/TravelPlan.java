package first;

import first.basics.Attraction;

import java.time.LocalDate;

public class TravelPlan {
    Trip trip;
    LocalDate startDate, endDate;

    boolean bipGraph[][]=new boolean[110][110];
    int N,M;
    public TravelPlan(){
        trip=new Trip();
        startDate=LocalDate.now();
        endDate=startDate.plusDays(10);
    }
    public TravelPlan(int nrDays){
        trip=new Trip(nrDays);
        startDate=LocalDate.now();
        endDate=startDate.plusDays(nrDays);
    }
    public TravelPlan(int nrDays, Trip t){
        trip=t;
        startDate=LocalDate.now();
        endDate=startDate.plusDays(nrDays);
    }
    public void makeTravelPlan(){
        int indA=0, indD=0;
        for(indD=0; indD<=(endDate.getDayOfYear()-startDate.getDayOfYear()); indD++){
            for(indA=0; indA<trip.lista.size(); ++indA){
                if(trip.lista.get(indA).isVisitable(startDate.plusDays(indD))==true){
                    bipGraph[indD][indA]=true;
                }
                else bipGraph[indD][indA]=false;
                M=(indA>M)?indA:M;
            }
            N=(indD>N)?indD:N;
        }
        N++; M++;
        int[] plan=new int[110];
        plan=maxBipMatching(bipGraph);
        indD=1;
        for(indD=0; indD<N; indD++){
            if(plan[indD]>-1){
                System.out.println("On the "+startDate.plusDays(indD)+" the "+trip.lista.get(plan[indD])+" will be visited");
            }
            indD++;
        }
    }
    boolean matched(boolean bipartiteGraph[][], int u, boolean visited[], int match[])
    {
        for (int v = 0; v < N; v++)
        {
            if (bipartiteGraph[u][v] && !visited[v])
            {
                visited[v] = true;
                if (match[v] < 0 || matched(bipartiteGraph, match[v], visited, match))
                {
                    match[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    int[] maxBipMatching(boolean bipartiteGraph[][])
    {
        int match[] = new int[N];
        
        for(int i = 0; i < N; ++i)
            match[i] = -1;

        int result = 0;
        for (int u = 0; u < M; u++)
        {
            boolean visited[] =new boolean[N] ;
            for(int i = 0; i < N; ++i)
                visited[i] = false;

            if (matched(bipartiteGraph, u, visited, match))
                result++;
        }
        return match;
    }
    

}
