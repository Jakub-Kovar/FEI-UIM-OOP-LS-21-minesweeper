package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.game.tiles.Empty;
import sk.stuba.fei.uim.oop.game.tiles.Mine;
import sk.stuba.fei.uim.oop.game.tiles.MineRevealedException;
import sk.stuba.fei.uim.oop.game.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    public static final int BOARD_SIZE = 9;
    public static final int MINE_COUNT = 10;

    private Tile[][] board;

    public Board() {
        this.board = new Tile[BOARD_SIZE][BOARD_SIZE];
        fillBoard();
    }

    private void fillBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = new Empty();
            }
        }

        Random rand = new Random();
        int minesLeft = MINE_COUNT;
        while (minesLeft > 0) {
            int mx = rand.nextInt(BOARD_SIZE);
            int my = rand.nextInt(BOARD_SIZE);
            if (this.board[my][mx] instanceof Empty) {
                this.board[my][mx] = new Mine();
                minesLeft--;
            }
        }

        this.addTileNeighbours();
    }

    public String draw() {
        StringBuilder builder = new StringBuilder();

        builder.append("  012345678\n");

        for (int i = 0; i < BOARD_SIZE; i++) {
            builder.append((char) ('a' + i));
            builder.append(" ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                builder.append(this.board[i][j].draw());
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public boolean isBoardRevealed() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!this.board[i][j].isRevealed() && !(this.board[i][j] instanceof Mine)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void reveal(Move move) throws MineRevealedException {
        this.board[move.y][move.x].reveal();
    }

    private void addTileNeighbours() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (this.board[i][j] instanceof Empty) {
                    Empty empty = (Empty) this.board[i][j];
                    List<Tile> neighbours = new ArrayList<>();
                    if (i - 1 >= 0) {
                        neighbours.add(this.board[i - 1][j]);
                        if (j - 1 >= 0) {
                            neighbours.add(this.board[i - 1][j - 1]);
                        }
                        if (j + 1 < BOARD_SIZE) {
                            neighbours.add(this.board[i - 1][j + 1]);
                        }
                    }
                    if (i + 1 < BOARD_SIZE) {
                        neighbours.add(this.board[i + 1][j]);
                        if (j - 1 >= 0) {
                            neighbours.add(this.board[i + 1][j - 1]);
                        }
                        if (j + 1 < BOARD_SIZE) {
                            neighbours.add(this.board[i + 1][j + 1]);
                        }
                    }
                    if (j - 1 >= 0) {
                        neighbours.add(this.board[i][j - 1]);
                    }
                    if (j + 1 < BOARD_SIZE) {
                        neighbours.add(this.board[i][j + 1]);
                    }
                    empty.addNeighbours(neighbours);
                }
            }
        }
    }
}
