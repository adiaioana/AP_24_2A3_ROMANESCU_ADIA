package org.example.ex13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapSolver {
    boolean[][] map;
    int nrThreads=1;
    int nrNodes=0;

    public MapSolver(int noThreads, boolean[][] aMatrix, int noNodes){
        nrThreads=Math.max(1,noThreads);
        map=aMatrix;
        nrNodes=noNodes;
    }
    public void begin(){
        boolean[] vis=new boolean[nrNodes+10];
        vis[0]=true;
        List<Integer> nodes=new ArrayList<>(nrNodes+10);
        nodes.add(0);
        var solver=new DFS(nrNodes,nrThreads,map,vis,0,nodes);
        solver.run();
    }
}
