package State;

import MVC.Pair;
import MVC.Cell;
import MVC.MineSweeperModel;
import Singleton.MineSweeperGameSingletone;

public class FlaggedState implements CellState{

    @Override
    public void reveal(Cell cell) {}

    @Override
    public void toggleFlag(Cell cell) {
        cell.setState(new HiddenState());
        Pair row_col = MineSweeperGameSingletone.getInstance().getModel().getCell(cell);
        MineSweeperGameSingletone.getInstance().getView().setFlag(row_col.first(), row_col.second(),false);
    }
}
