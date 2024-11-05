package Factory;

import MVC.Cell;

public class CellFactory {
    public static Cell createCell(boolean isMine, int adjacentMines) {
        if (isMine) {
            return new MineCell();
        }
        else if(adjacentMines < 0) {
            return new NumberCell(adjacentMines);
        }
        else {
            return new EmptyCell();
        }
    }
}
