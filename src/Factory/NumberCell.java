package Factory;

import MVC.Cell;

public class NumberCell extends Cell {
    public NumberCell(int adjacentMines) {
        setAdjacentMines(adjacentMines);
    }

    @Override
    public String display() {
        return isRevealed() ? String.valueOf(getAdjacentMines()) : (isFlagged() ? "F" : "");
    }
}
