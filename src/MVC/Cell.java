package MVC;

public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int adjacentMines;

    public boolean isMine() { return isMine; }
    public void setMine(boolean isMine) { this.isMine = isMine; }

    public boolean isRevealed() { return isRevealed; }
    public void setRevealed(boolean isRevealed) { this.isRevealed = isRevealed; }

    public boolean isFlagged() { return isFlagged; }
    public void setFlagged(boolean isFlagged) { this.isFlagged = isFlagged; }

    public int getAdjacentMines() { return adjacentMines; }
    public void setAdjacentMines(int adjacentMines) { this.adjacentMines = adjacentMines; }
}
