package vrp;

public class Bonus3 {
    public final double MAXTRAVEL = 100000;
    boolean visited[][]=new boolean[110][110];
    String matr[][];
    double cost[][]=new double[110][110];
    int nrClients, nrDepots,nrLines,nrCollumns;
    double[][] travelDepoToClient;
    double[][] travelClientToClient;
    public Bonus3(){
        nrClients=nrDepots=nrLines=nrCollumns=0;
        travelClientToClient = new double[110][110];
        travelDepoToClient = new double[110][110];
        matr=new String[110][110];
        for(int i=0; i<=100; ++i)
            for(int j=0; j<=100; ++j) {
                visited[i][j]=false;
                cost[i][j]=10*MAXTRAVEL;
            }
    }
    public Bonus3(double[][] costDC, double[][] costCC, int nrD, int nrC, String[][]matty,int nrl, int nrc) {
        nrClients = nrC;
        nrDepots = nrD;
        travelClientToClient = costCC;
        travelDepoToClient = costDC;
        matr=matty;
        nrLines=nrl;
        nrCollumns=nrc;
        for(int i=1; i<=nrLines; ++i)
            for(int j=1; j<=nrCollumns; ++j)
            if(!matr[i][j].isEmpty() && matr[i][j].contains("C")){
                visited[i][j]=false;
                cost[i][j]=10*MAXTRAVEL;
            }
            else if(!matr[i][j].isEmpty()){
                cost[i][j]=0;
                visited[i][j]=true;
            }
    }
    public double minim(double A, double B){
        return ((A<B)?A:B);
    }
    public double calculatedCost(int x1, int y1, int x2, int y2) {
        int tp1=0, tp2=0;
        tp1=(matr[x1][y1].contains("D"))?1:2;
        tp2=(matr[x2][y2].contains("D"))?1:2;

        int v1=Integer.valueOf(matr[x1][y1].substring(1));
        int v2=Integer.valueOf(matr[x2][y2].substring(1));

        if(tp1==1 && tp2==1)
            return MAXTRAVEL*2;
        if(tp1==1) {
            return travelDepoToClient[v1][v2];
        }
        else if(tp2==1){
            return travelDepoToClient[v2][v1];
        }
        return travelClientToClient[v1][v2];
    }
    void filly(int x,int y){
        int[] dx={0,-1,0,1,0};
        int[] dy={0,0,1,0,-1};
        
        for(int d=1; d<=4; ++d){
            int newX=x+dx[d];
            int newY=y+dy[d];
            if(newX>0 && newY>0 && newX<=nrLines && newY<=nrCollumns){
                if(!visited[newX][newY]) {
                    visited[newX][newY]=true;
                    filly(newX,newY);
                }
                cost[x][y]=minim(cost[x][y],cost[newX][newY]+calculatedCost(x,y,newX,newY));
            }
        }
    }
    public double solve(){
        for(int i=1; i<=nrLines; i++)
            for(int j=1; j<=nrCollumns; ++j)
                if(!visited[i][j] && matr[i][j].contains("C")){
                    visited[i][j]=true;
                    filly(i,j);
                }

        double ans=0;
        for(int i=1; i<=nrLines; i++)
            for(int j=1; j<=nrCollumns; ++j)
                if(visited[i][j] && matr[i][j].contains("C")){
                    ans+=cost[i][j];
                }
        return ans;
    }

}
