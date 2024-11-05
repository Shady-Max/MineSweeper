package Command;

import MVC.Cell;
import MVC.MineSweeperModel;
import MVC.MineSweeperView;

public class ToggleFlagCommand implements Command {

    private final MineSweeperModel model;
    private final MineSweeperView view;
    private final int row;
    private final int col;

    public ToggleFlagCommand(MineSweeperModel model, MineSweeperView view, int row, int col) {
        this.model = model;
        this.view = view;
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute() {
        Cell cell = model.getCell(row, col);
        if (!cell.isRevealed()) {
            cell.setFlagged(!cell.isFlagged());
            view.setFlag(row, col, cell.isFlagged());
        }
    }
}
