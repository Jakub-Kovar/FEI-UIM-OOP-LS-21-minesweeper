package sk.stuba.fei.uim.oop.game;

public class Game {

    private Board board;

    public Game() {
        this.board = new Board();
    }

    public void play() {
        System.out.println(this.board.draw());
    }
}
