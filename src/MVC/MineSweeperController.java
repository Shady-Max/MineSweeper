package MVC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeperController {

    private final MineSweeperModel model;
    private final MineSweeperView view;
    private Timer gameTimer;
    private int elapsedSeconds = 0;

    public MineSweeperController(MineSweeperModel model, MineSweeperView view) {
        this.model = model;
        this.view = view;

        view.setCellClickListener((row, col, isFlagClick) -> {
            if (isFlagClick) {
                toggleFlag(row, col);
            } else {
                handleCellClick(row, col);
            }
        });

        view.setMinesCount(model.getTotalMines());

        startTimer();
    }

    private void startTimer() {
        elapsedSeconds = 0;
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedSeconds++;
                view.setTimeCount(elapsedSeconds);
            }
        });
        gameTimer.start();
    }

    private void handleCellClick(int row, int col) {
        Cell cell = model.getCell(row, col);

        if (cell.isFlagged() || cell.isRevealed()) return;

        if (cell.isMine()) {
            view.showMine(row, col);
            view.showGameOver();
            return;
        }

        revealCell(row, col);

        if (isWinConditionMet()) {
            view.showWinMessage();
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
                    if (r >= 0 && r < model.getRows() && c >= 0 && c < model.getCols()) {
                        revealCell(r, c);
                    }
                }
            }
        }
    }

    private boolean isWinConditionMet() {
        for (int row = 0; row < model.getRows(); row++) {
            for (int col = 0; col < model.getCols(); col++) {
                Cell cell = model.getCell(row, col);
                if (!cell.isMine() && !cell.isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void toggleFlag(int row, int col) {
        Cell cell = model.getCell(row, col);
        if (!cell.isRevealed()) {
            cell.setFlagged(!cell.isFlagged());
            view.setFlag(row, col, cell.isFlagged());
        }
    }
}
