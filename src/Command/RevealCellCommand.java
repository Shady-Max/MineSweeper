package Command;

import MVC.Cell;

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
