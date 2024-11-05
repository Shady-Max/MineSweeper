package Factory;

import MVC.Cell;

public class EmptyCell extends Cell {
    @Override
    public String display () {
        return isRevealed() ? " " : (isFlagged() ? "F" : "");
    }
}
