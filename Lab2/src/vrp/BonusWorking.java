package vrp;

import java.util.Vector;

public class BonusWorking {

    BonusGraph initialData;
    Position[] depos;
    Position[] clis;
    public BonusWorking(){
        initialData=new BonusGraph();
    }
    public void filly(int[][] listaAdiacenta, int nod){
        for(int i=1; i<=listaAdiacenta[nod][0]; ++i)
        {
            int currNode=listaAdiacenta[nod][i];
            if(currNode<=initialData.getNrDepots()){
                depos[currNode]=new Position();
            }

        }
    }
    public BonusWorking(double[][] costDC, double[][] costCC, int nrD, int nrC){
        initialData=new BonusGraph(costDC,costCC,nrD,nrC);

        int [][] lista=new int[110][22];
        for(int i=1; i<=nrD+nrC; ++i)
            for(int j=1; j<=nrD+nrC; ++j) {
                lista[i][j]=0;
            }
        Vector<Integer> coada=new Vector<Integer>(nrC+nrD+20);
        for(int i=1; i<=nrD+nrC; ++i)
            for(int j=1; j<=nrC; ++j)
                if((i<=nrD && costDC[i][j]>0)||(i>nrD && costCC[i-nrD][j]>0)) {
                    lista[i][++lista[i][0]] = (j + nrD);
                    lista[j + nrD][++lista[j + nrD][0]]=i;
                }
        for(int degree=4; degree>0; --degree)
            for(int i=1; i<=nrD+nrC; ++i)
                if(lista[i][0]==degree)
                    coada.add(i);
        int inc=0;
        int initx=nrC+nrD, inity=nrC+nrD;
        while(!coada.isEmpty()) {
            int node=coada.firstElement(); coada.removeFirst();
            if(node<=nrD) {
                if(depos[node].x==10000 && depos[node].y==10000){
                    depos[node]=new Position(initx,inity);
                    filly(lista,node);
                }
            }
            else {
                node-=nrD;
                if(clis[node].x==10000 && clis[node].y==10000){
                    clis[node]=new Position(initx,inity);
                    filly(lista, node+nrD);
                }
            }


        }

    }

    public BonusGraph getInitialData() {
        return initialData;
    }
    public void setInitialData(BonusGraph initialData) {
        this.initialData = initialData;
    }
}
