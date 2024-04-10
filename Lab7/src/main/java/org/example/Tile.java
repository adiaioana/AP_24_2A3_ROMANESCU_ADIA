package org.example;

public class Tile {
    private int left,right;
    public Tile(int x,int y){
        left=x; right=y;
    }
    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }
}
