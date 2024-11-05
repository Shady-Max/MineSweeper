package MVC;

import Command.*;
import Singleton.MineSweeperGameSingletone;

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
            Command command;
            if (isFlagClick) {
                command = new ToggleFlagCommand(model, view, row, col);//toggleFlag(row, col);
            } else {
                command = new RevealCellCommand(model, view, row, col); //handleCellClick(row, col);
            }
            command.execute();
        });

        view.setMinesCount(MineSweeperGameSingletone.getInstance().getMines());

        startTimer();
    }

    private void startTimer() {
        elapsedSeconds = 0;
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MineSweeperGameSingletone.getInstance().isGameWon() || MineSweeperGameSingletone.getInstance().isGameLost()) {
                    stopTimer();
                    return;
                }

                elapsedSeconds++;
                view.setTimeCount(elapsedSeconds);
            }
        });
        gameTimer.start();
    }

    private void stopTimer() {
        if (gameTimer == null) { return; }
        gameTimer.stop();
    }
}
