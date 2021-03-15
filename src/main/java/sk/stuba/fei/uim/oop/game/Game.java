package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.game.tiles.MineRevealedException;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Game {

    private Board board;

    public Game() {
        this.board = new Board();
    }

    public void play() {
        try {
            while(!this.board.isBoardRevealed()) {
                drawBoard();
                this.board.reveal(getNextMove());
            }
        } catch (MineRevealedException e) {
            System.out.println("You stepped on a mine!");
            drawBoard();
            return;
        }
        System.out.println("You have revealed the whole board!");
        drawBoard();
    }

    private Move getNextMove() {
        char row = ZKlavesnice.readChar(String.format("Enter row (a-%s)", (char)('a' + Board.BOARD_SIZE - 1)));
        int col = ZKlavesnice.readInt(String.format("Enter column (0-%d)", Board.BOARD_SIZE - 1));
        return new Move(col, row);
    }

    private void drawBoard() {
        System.out.println(this.board.draw());
    }
}
