package State;

import MVC.Cell;
import MVC.MineSweeperModel;
import MVC.Pair;
import Singleton.MineSweeperGameSingletone;

public class HiddenState implements CellState{

    @Override
    public void reveal(Cell cell) {
        if (cell.isFlagged() || cell.isRevealed()) return;

        if (cell.isMine()) {
            revealMines();
            MineSweeperGameSingletone.getInstance().setGameLost();
            MineSweeperGameSingletone.getInstance().getView().showGameOver();
            return;
        }

        Pair row_col= MineSweeperGameSingletone.getInstance().getModel().getCell(cell);
        revealCell(row_col.first(), row_col.second());

        if (MineSweeperGameSingletone.getInstance().isWinConditionMet()) {
            MineSweeperGameSingletone.getInstance().getView().showWinMessage();
        }
    }

    @Override
    public void toggleFlag(Cell cell) {
        cell.setState(new FlaggedState());
        Pair row_col= MineSweeperGameSingletone.getInstance().getModel().getCell(cell);
        MineSweeperGameSingletone.getInstance().getView().setFlag(row_col.first(), row_col.second(),true);
    }

    private void revealMines() {
        MineSweeperGameSingletone singleton = MineSweeperGameSingletone.getInstance();
        for (int row = 0; row < singleton.getRows(); row++) {
            for (int col = 0; col < singleton.getCols(); col++) {
                if (singleton.getBoard()[row][col].isMine()) {
                    singleton.getBoard()[row][col].setRevealed(true);
                    MineSweeperGameSingletone.getInstance().getView().showMine(row, col);
                }
            }
        }
    }

    private void revealCell(int row, int col) {
        Cell cell = MineSweeperGameSingletone.getInstance().getModel().getCell(row, col);
        if (cell.isRevealed()) return;

        cell.setRevealed(true);
        MineSweeperGameSingletone.getInstance().getView().revealCell(row, col, cell.getAdjacentMines());

        if (cell.getAdjacentMines() == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int r = row + i;
                    int c = col + j;
                    if (MineSweeperGameSingletone.getInstance().isInBounds(r, c)) {
                        revealCell(r, c);
                    }
                }
            }
        }
    }
}
