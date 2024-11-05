package MVC;

import Command.*;
import Singleton.Singleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private final Model model;
    private final View view;
    private Timer gameTimer;
    private int elapsedSeconds = 0;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.setCellClickListener((row, col, isFlagClick) -> {
            Command command;
            Cell cell = model.getCell(row, col);
            if (isFlagClick) {
                command = new ToggleFlagCommand(cell);
            } else {
                command = new RevealCellCommand(cell);
            }
            command.execute();
        });

        view.setMinesCount(Singleton.getInstance().getMines());

        startTimer();
    }

    public void startTimer() {
        elapsedSeconds = 0;
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Singleton.getInstance().isGameWon() || Singleton.getInstance().isGameLost()) {
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
