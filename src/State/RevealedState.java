package State;

import MVC.Cell;

public class RevealedState implements CellState {

    @Override
    public void reveal(Cell cell) {
        cell.setFlagged(false);
    }

    @Override
    public void toggleFlag(Cell cell) {
        cell.setFlagged(false);
    }
}
