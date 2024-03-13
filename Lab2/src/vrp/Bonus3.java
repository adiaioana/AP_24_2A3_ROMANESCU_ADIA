package vrp;

public class Bonus3 {
    int[] dx = {0, -1, 0, 1, 0};
    int[] dy = {0, 0, 1, 0, -1};
    public final double MAXTRAVEL = 100000;
    int mark[][] = new int[110][110];
    int antx[][] = new int[110][110];
    int anty[][] = new int[110][110];
    boolean visited[][] = new boolean[110][110];
    String matr[][];
    double cost[][] = new double[110][110];
    double lee[][][][] = new double[110][110][110][110];
    boolean visitedLee[][][][] = new boolean[110][110][110][110];
    public int nrClients, nrDepots, nrLines, nrCollumns;
    double[][] travelDepoToClient;
    double[][] travelClientToClient;

    public Bonus3() {
        nrClients = nrDepots = nrLines = nrCollumns = 0;
        travelClientToClient = new double[110][110];
        travelDepoToClient = new double[110][110];
        matr = new String[110][110];
        for (int i = 0; i <= 100; ++i)
            for (int j = 0; j <= 100; ++j) {
                visited[i][j] = false;
                cost[i][j] = 10 * MAXTRAVEL;
                mark[i][j] = 0;
            }
        for (int i = 0; i <= 100; ++i)
            for (int j = 0; j <= 100; ++j)
                for (int k = 0; k <= 100; ++k)
                    for (int l = 0; l <= 100; ++l) {
                        visitedLee[i][j][k][l] = false;
                        lee[i][j][l][k] = 10 * MAXTRAVEL;
                    }
    }

    public Bonus3(double[][] costDC, double[][] costCC, int nrD, int nrC, String[][] matty, int nrl, int nrc) {
        nrClients = nrC;
        nrDepots = nrD;
        travelClientToClient = costCC;
        travelDepoToClient = costDC;
        matr = matty;
        nrLines = nrl;
        nrCollumns = nrc;

        for (int i = 0; i <= 100; ++i)
            for (int j = 0; j <= 100; ++j)
                for (int k = 0; k <= 100; ++k)
                    for (int l = 0; l <= 100; ++l) {
                        visitedLee[i][j][k][l] = false;
                        lee[i][j][l][k] = 10 * MAXTRAVEL;
                    }
        for (int i = 1; i <= nrLines; ++i)
            for (int j = 1; j <= nrCollumns; ++j)
                if (!matr[i][j].isEmpty() && matr[i][j].contains("C")) {
                    visited[i][j] = false;
                    cost[i][j] = 10 * MAXTRAVEL;
                    mark[i][j] = 0;
                    antx[i][j] = anty[i][j] = 1;
                } else if (!matr[i][j].isEmpty()) {
                    cost[i][j] = 0;
                    mark[i][j] = Integer.valueOf(matr[i][j].substring(1));
                    visited[i][j] = true;
                }
    }

    public double minim(double A, double B) {
        return ((A < B) ? A : B);
    }

    public double calculatedCost(int x1, int y1, int x2, int y2) {
        int tp1 = 0, tp2 = 0;
        tp1 = (matr[x1][y1].contains("D")) ? 1 : 2;
        tp2 = (matr[x2][y2].contains("D")) ? 1 : 2;

        int v1 = Integer.valueOf(matr[x1][y1].substring(1));
        int v2 = Integer.valueOf(matr[x2][y2].substring(1));

        if (tp1 == 1 && tp2 == 1)
            return MAXTRAVEL * 2;
        if (tp1 == 1) {
            return travelDepoToClient[v1][v2];
        } else if (tp2 == 1) {
            return travelDepoToClient[v2][v1];
        }
        return travelClientToClient[v1][v2];
    }

    void filly(int bx, int by, int x, int y) {


        for (int d = 1; d <= 4; ++d) {
            int newX = x + dx[d];
            int newY = y + dy[d];
            if (newX > 0 && newY > 0 && newX <= nrLines && newY <= nrCollumns) {
                if (!visitedLee[bx][by][newX][newY]) {
                    visitedLee[bx][by][newX][newY] = true;
                    filly(bx, by, newX, newY);
                }
                if (cost[newX][newY] >= lee[bx][by][newX][newY] + calculatedCost(x, y, newX, newY) ||
                        lee[bx][by][newX][newY]>=lee[bx][by][newX][newY] + calculatedCost(x, y, newX, newY) ) {
                    mark[x][y] = mark[bx][by];
                    antx[newX][newY] = x;
                    anty[newX][newY] = y;
                }
                cost[newX][newY] = minim(lee[bx][by][newX][newY] + calculatedCost(x, y, newX, newY), cost[newX][newY]);
                lee[bx][by][newX][newY] = minim(lee[bx][by][newX][newY], lee[bx][by][newX][newY] + calculatedCost(x, y, newX, newY));
            }
        }
    }

    public double abs(double X) {
        return (X > 0 ? X : -X);
    }


    public double solve() {
        int cx1, cx2, cy1, cy2;
        double ans = 0;

        for (int i = 1; i <= nrLines; i++)
            for (int j = 1; j <= nrCollumns; ++j)
                if (matr[i][j].contains("D")) {
                    cx1 = cx2 = cy1 = cy2 = 0;
                    for (int d = 1; d <= 4; ++d) {
                        int newX = i + dx[d];
                        int newY = j + dy[d];
                        if (newX > 0 && newY > 0 && newX <= nrLines && newY <= nrCollumns && mark[newX][newY] == 0 && matr[newX][newY].contains("C")) {
                            if (cx1 == 0) {
                                cx1 = newX;
                                cy1 = newY;
                            } else {
                                cx2 = newX;
                                cy2 = newY;
                                break;
                            }
                        }
                    }
                    if (cx2 == 0) {
                        System.out.println("UNSOLVABLE");
                        return 0;
                    }

                    visitedLee[i][j][cx1][cy1] = true;
                    mark[cx1][cy1] = mark[i][j];
                    antx[cx1][cy1] = i;
                    anty[cx1][cy1] = j;
                    cost[cx1][cy1] = calculatedCost(i, j, cx1, cy1);
                    lee[i][j][cx1][cy1] = calculatedCost(i, j, cx1, cy1);

                    visitedLee[i][j][cx2][cy2] = true;
                    mark[cx2][cy2] = mark[i][j];
                    antx[cx2][cy2] = i;
                    anty[cx2][cy2] = j;
                    cost[cx2][cy2] = calculatedCost(i, j, cx2, cy2);
                    lee[i][j][cx2][cy2] = calculatedCost(i, j, cx2, cy2);

                    filly(i, j, cx1, cy1);
                    filly(i, j, cx2, cy2);
                    ans+=lee[i][j][cx1][cy1];
                    ans+=lee[i][j][cx2][cy2];
                }
    double ans1=0;
        for (int i = 1; i <= nrLines; i++)
            for (int j = 1; j <= nrCollumns; ++j)
            if(matr[i][j].contains("C")){
                for (int d = 1; d <= 4; ++d) {
                int newX = i + dx[d];
                int newY = j + dy[d];
                if (newX>0 &&newY>0 &&newX<=nrLines && newY<=nrCollumns && (antx[newX][newY]==i && anty[newX][newY]==j) ||
                antx[i][j]==newX &&anty[i][j]==newY){
                    ans1+=calculatedCost(i,j,newX,newY);
                }
            }
            }
        return ans1;
    }

    }

