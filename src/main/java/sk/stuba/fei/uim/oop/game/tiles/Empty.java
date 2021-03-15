package sk.stuba.fei.uim.oop.game.tiles;

import java.util.ArrayList;
import java.util.List;

public class Empty extends Tile {

    private int nearbyMines;

    private List<Tile> neighbours;

    public Empty() {
        this.nearbyMines = 0;
        this.neighbours = new ArrayList<>();
    }

    @Override
    public void reveal() throws MineRevealedException {
        boolean wasHidden = !this.isRevealed();
        super.reveal();
        if (this.nearbyMines == 0 && wasHidden) {
            for (Tile t : this.neighbours) {
                t.reveal();
            }
        }
    }

    @Override
    protected String drawRevealed() {
        if (nearbyMines == 0) {
            return ".";
        }
        return nearbyMines + "";
    }

    public void addNeighbours(List<Tile> neighbours) {
        this.neighbours.addAll(neighbours);
        neighbours.forEach(tile -> {
            if (tile instanceof Mine) {
                this.nearbyMines++;
            }
        });
    }
}
