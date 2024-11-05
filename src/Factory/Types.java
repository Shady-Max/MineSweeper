// Клетка с миной
public class MineCell extends Cell {
    public MineCell() {
        this.isMine = true;
    }

    @Override
    public String display() {
        return isRevealed ? "*" : (isFlagged ? "F" : "");
    }
}

// Клетка с числом
public class NumberCell extends Cell {
    public NumberCell(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    @Override
    public String display() {
        return isRevealed ? String.valueOf(adjacentMines) : (isFlagged ? "F" : "");
    }
}

// Пустая клетка
public class EmptyCell extends Cell {
    @Override
    public String display() {
        return isRevealed ? " " : (isFlagged ? "F" : "");
    }
}
