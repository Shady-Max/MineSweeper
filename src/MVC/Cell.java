package MVC;

import State.CellState;
import State.HiddenState;

public abstract class Cell {
    private CellState state;
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int adjacentMines;

    public Cell () {
        state = new HiddenState();
    }

    public boolean isMine() { return isMine; }
    public void setMine(boolean isMine) { this.isMine = isMine; }

    public boolean isRevealed() { return isRevealed; }
    public void setRevealed(boolean isRevealed) { this.isRevealed = isRevealed; }

    public boolean isFlagged() { return isFlagged; }
    public void setFlagged(boolean isFlagged) { this.isFlagged = isFlagged; }

    public int getAdjacentMines() { return adjacentMines; }
    public void setAdjacentMines(int adjacentMines) { this.adjacentMines = adjacentMines; }

    public void setState(CellState state) {
        this.state = state;
    }

    public void reveal() {
        state.reveal(this);
    }

    public void toggleFlag() {
        state.toggleFlag(this);
    }

    // Method for displaying cells' status
    public abstract String display();

}