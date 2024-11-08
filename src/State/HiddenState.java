package State;

import MVC.Cell;
import MVC.Pair;
import Singleton.Singleton;

public class HiddenState implements CellState{

    @Override
    public void reveal(Cell cell) {
        if (cell.isFlagged() || cell.isRevealed()) return;

        if (cell.isMine()) {
            revealMines();
            Singleton.getInstance().setGameLost();
            return;
        }

        Pair row_col= Singleton.getInstance().getModel().getCell(cell);
        revealCell(row_col.first(), row_col.second());
        Singleton.getInstance().isWinConditionMet();
    }

    @Override
    public void toggleFlag(Cell cell) {
        cell.setState(new FlaggedState());
        cell.setFlagged(true);
        Pair row_col= Singleton.getInstance().getModel().getCell(cell);
        Singleton.getInstance().getView().setFlag(row_col.first(), row_col.second(),true);
        Singleton.getInstance().isWinConditionMet();
    }

    private void revealMines() {
        Singleton singleton = Singleton.getInstance();
        for (int row = 0; row < singleton.getRows(); row++) {
            for (int col = 0; col < singleton.getCols(); col++) {
                if (singleton.getBoard()[row][col].isMine()) {
                    singleton.getBoard()[row][col].setRevealed(true);
                    Singleton.getInstance().getView().showMine(row, col);
                }
            }
        }
    }

    private void revealCell(int row, int col) {
        Cell cell = Singleton.getInstance().getModel().getCell(row, col);
        if (cell.isRevealed()) return;

        cell.setRevealed(true);
        cell.setState(new RevealedState());

        Singleton.getInstance().getView().revealCell(row, col, cell.getAdjacentMines());

        if (cell.getAdjacentMines() == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int r = row + i;
                    int c = col + j;
                    if (Singleton.getInstance().isInBounds(r, c)) {
                        revealCell(r, c);
                    }
                }
            }
        }
    }
}
