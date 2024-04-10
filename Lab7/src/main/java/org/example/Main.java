package org.example;

public class Main {
    public static void main(String[] args) {
        var adia=new Main();
        adia.compulsory();
    }
    public void compulsory(){
        Game game = new Game();
        game.addPlayer(new Player("Player 1",game.bag));
        game.addPlayer(new Player("Player 2",game.bag));
        game.addPlayer(new Player("Player 3",game.bag));
        game.play();
    }
}