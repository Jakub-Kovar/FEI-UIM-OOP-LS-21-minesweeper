package sk.stuba.fei.uim.oop.game;

public class Move {
    public int x;
    public int y;

    public Move(int x, char row) {
        this.x = x;
        this.y = row - 'a';
    }
}
