package State;

import MVC.Pair;
import MVC.Cell;
import Singleton.Singleton;

public class FlaggedState implements CellState{

    @Override
    public void reveal(Cell cell) {}

    @Override
    public void toggleFlag(Cell cell) {
        cell.setState(new HiddenState());
        cell.setFlagged(false);
        Pair row_col = Singleton.getInstance().getModel().getCell(cell);
        Singleton.getInstance().getView().setFlag(row_col.first(), row_col.second(),false);
    }
}
