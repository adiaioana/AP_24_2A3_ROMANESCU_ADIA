package org.example.ex13;

import java.io.Console;
import java.util.List;

public class DFS extends Thread {
    int noNodes;
    boolean[] visitedNodes;
    List<Integer> nodesInOrder;
    int startingNode;
    int maxThreads;
    boolean[][] graphAdj;
    synchronized boolean isVisited(int x)
    {
        return (boolean) visitedNodes[x];
    }
    boolean areAdjacent(int a, int b){
        return (graphAdj[a][b]|graphAdj[b][a]);
    }

    public DFS(int n, int req,boolean[][] mat, boolean[] vis, int node, List<Integer> nodes){
        startingNode=node;
        noNodes=n;
        visitedNodes=vis;
        maxThreads=req;
        graphAdj=mat;
        nodesInOrder=nodes;
    }

    @Override
    public void run(){
        int noStartedThreads=0;
        for(int index=0; index<noNodes; index++)
            if(areAdjacent(startingNode,index)){
                if(isVisited(index)){
                    System.out.println("Found: "+nodesInOrder);
                }
                else{
                    if(noStartedThreads<maxThreads){
                        var newVis=visitedNodes; newVis[index]=true;
                        var newOrder=nodesInOrder; newOrder.add(index);
                        var newDFS=new DFS(noNodes,maxThreads-noStartedThreads,graphAdj,newVis,index,newOrder);
                        noStartedThreads++;
                        newDFS.run();
                    }
                }
            }
    }
    
}
