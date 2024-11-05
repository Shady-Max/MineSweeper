package Command;

import MVC.Cell;

public class ToggleFlagCommand implements Command {
    private final Cell cell;

    public ToggleFlagCommand(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void execute() {
        cell.toggleFlag();
    }
}
