package Command;

import MVC.Cell;
import MVC.MineSweeperModel;
import MVC.MineSweeperView;
import Singleton.MineSweeperGameSingletone;

public class RevealCellCommand implements Command {
    private final MineSweeperModel model;
    private final MineSweeperView view;
    private final int row;
    private final int col;

    public RevealCellCommand(MineSweeperModel model, MineSweeperView view, int row, int col) {
        this.model = model;
        this.view = view;
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute() {
        Cell cell = model.getCell(row, col);

        if (cell.isFlagged() || cell.isRevealed()) return;

        if (cell.isMine()) {
            revealMines();
            view.showGameOver();
            return;
        }

        revealCell(row, col);

        if (MineSweeperGameSingletone.getInstance().isWinConditionMet()) {
            view.showWinMessage();
        }
    }

    private void revealMines() {
        MineSweeperGameSingletone singleton = MineSweeperGameSingletone.getInstance();
        for (int row = 0; row < singleton.getRows(); row++) {
            for (int col = 0; col < singleton.getCols(); col++) {
                if (singleton.getBoard()[row][col].isMine()) {
                    singleton.getBoard()[row][col].setRevealed(true);
                    view.showMine(row, col);
                }
            }
        }
    }

    private void revealCell(int row, int col) {
        Cell cell = model.getCell(row, col);
        if (cell.isRevealed()) return;

        cell.setRevealed(true);
        view.revealCell(row, col, cell.getAdjacentMines());

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
