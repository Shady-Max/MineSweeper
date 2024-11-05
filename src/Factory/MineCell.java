package Factory;

import MVC.Cell;

public class MineCell extends Cell {
    public MineCell() {
        setMine(true);
    }

    @Override
    public String display() {
        return isRevealed() ? "*" : (isFlagged() ? "F" : "");
    }
}
