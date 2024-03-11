package vrp;

import java.io.PipedOutputStream;

public class Position {
    protected int x,y;
    public Position(){
        x=-10000;
        y=-10000;
    }
    public Position(int a, int b){
        x=a;
        y=b;
    }
}
