package Factory;

import MVC.Cell;
import Proxy.CellProxy;

public class CellFactory {
    public static CellProxy createCell(boolean isMine, int adjacentMines) {
        Cell cell;
        if (isMine) {
            cell = new MineCell();
        }
        else if(adjacentMines < 0) {
            cell = new NumberCell(adjacentMines);
        }
        else {
            cell = new EmptyCell();
        }
        return new CellProxy(cell);
    }
}
