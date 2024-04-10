package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Bag {
    private Set<Tile> unusedTiles=new HashSet<>() ;
    public Bag(int N){
        for(int i=1; i<=N; ++i)
            for(int j=1; j<=N; ++j)
            {
                var aTile=new Tile(i,j);
                unusedTiles.add(aTile);
            }
    }
    public boolean tilesIsEmpty(){
        return (unusedTiles.isEmpty());
    }
    public synchronized List<Tile> extractTiles(String name, int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (unusedTiles.isEmpty()) {
                break;
            }
            List<Tile> unTilesArray=(unusedTiles.stream().collect(Collectors.toList()));
            int lgSet=unTilesArray.size();
            Random rand = new Random();
            var extrTile=unTilesArray.get(rand.nextInt(lgSet));
            extracted.add(extrTile);
            unusedTiles.remove(extrTile);
        }
        System.out.println("Player "+name+" extracted "+ tilesString(extracted));
        return extracted;
    }
    public String tilesString(List<Tile> tiles){
        String sol="";
        for (Tile tl:tiles)
            sol+=("{"+tl.getLeft()+","+tl.getRight()+"}");
        return sol;
    }
}