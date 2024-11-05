package Command;

import MVC.Cell;
import MVC.MineSweeperModel;
import MVC.MineSweeperView;
import Singleton.MineSweeperGameSingletone;
import Observer.Observer;
import Observer.ObserverNotification;

public class RevealCellCommand implements Command {
    private final Cell cell;

    public RevealCellCommand(Cell cell){
        this.cell = cell;

    }

    @Override
    public void execute() {
        cell.reveal();
    }
}
