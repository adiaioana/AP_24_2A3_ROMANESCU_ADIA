package org.example.firstdemo.controller.problemsolving;

import org.example.firstdemo.controller.problemsolving.vrp.*;

import java.util.*;

public class ProblemData {
    private int depots;

    private int clients;

    private int vehicles;

    public ProblemData(int depValue, int cliValue, int vehValue){
        depots=depValue; clients=cliValue; vehicles=vehValue;
    }
    public String colorOfNode(String nodeType){
        Map<String,String> typeToColor= new HashMap<>();
        typeToColor.put("Depot","#ff0000");
        typeToColor.put("Client","#00ff00");
        typeToColor.put("Vehicle","#0000ff");

        for(var of:typeToColor.keySet())
            if(nodeType.contains(of))
                return typeToColor.get(of);
        return "#00000";
    }

    public Graph generateGraphFrom(){
        var rand=new Random();
        int nrNodes=clients+depots;
        int nrEdges=rand.nextInt(nrNodes/2)+Math.min(3,nrNodes/2);
        int x=0, y=0;
        int noCols=4;
        List<Graph.Node> generatedNodes=new ArrayList<>();
        Vector<Vehicle> vehs=new Vector<>(); Vector<Depot> depos = new Vector<>(); Vector<Client> clis= new Vector<>();


        for(int index=1; index<=nrNodes; index++){
            if(index<=depots)
                depos.add(new Depot(index));
            else clis.add(new Client(index-depots));

            String name=(index<=depots)?"Depot "+index:"Client "+(index-depots);
            y++; x++;
            generatedNodes.add(new Graph.Node("n"+((int)generatedNodes.stream().count()+1),name,x,y,5,colorOfNode(name)));
            y=y%noCols;
        }

        List<Graph.Edge> generatedEdges=new ArrayList<>();
        boolean[][] adj=new boolean[nrNodes+19][nrNodes+10];
        int edgeIndex=0;
        while(generatedEdges.size()<nrEdges){
            for(int first=1; first<=nrNodes; first++)
                for(int second=first+1; second<=nrNodes; second++)
                if(adj[first][second]==false && rand.nextBoolean()){
                    edgeIndex++;
                    generatedEdges.add(new Graph.Edge("e"+((int)generatedEdges.stream().count()+1),"n"+first,"n"+second,"#cccccc"));
                    adj[first][second]=adj[second][first]=true;
                }
        }
        for(int index=1; index<=vehicles; index++){
            var depoInd=rand.nextInt()%depots+1;
            vehs.add(new Vehicle(index,depoInd));
            generatedEdges.add(new Graph.Edge("e"+((int)generatedEdges.size()+1),"n"+depoInd,"n"+((int)generatedNodes.stream().count()+1),"#cccccc"));
            String name="Vehicle "+index;
            y++; x++;
            generatedNodes.add(new Graph.Node("n"+((int)generatedNodes.stream().count()+1),name,x,y,5,colorOfNode(name)));
            y=y%noCols;
        }
        var prob=new Problem(clis,depos,vehs);
        var sol=new Solution(prob);
        sol.start();
        System.out.println("The solution is "+sol.toString());
        return new Graph(generatedNodes,generatedEdges);
    }
    public String toString(){
        return "Location with data:\nDepots="+depots+"; Clients="+clients+"; Vehicles="+vehicles+"\n";
    }

    public int getDepots() {
        return depots;
    }

    public void setDepots(int depots) {
        this.depots = depots;
    }

    public int getClients() {
        return clients;
    }

    public void setClients(int clients) {
        this.clients = clients;
    }


    public int getVehicles() {
        return vehicles;
    }

    public void setVehicles(int vehicles) {
        this.vehicles = vehicles;
    }

}
