package State;

import MVC.Cell;

public interface CellState {
    void reveal(Cell cell);
    void toggleFlag(Cell cell);
}
