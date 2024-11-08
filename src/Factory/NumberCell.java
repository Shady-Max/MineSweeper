package Factory;

import MVC.Cell;

/**
 * Represents a cell on the Minesweeper board that has a specific number of adjacent mines.
 */
public class NumberCell extends Cell {
    /**
     * Constructor for a NumberCell, which sets the number of adjacent mines.
     * @param adjacentMines The number of mines adjacent to this cell.
     */
    public NumberCell(int adjacentMines) {
        setAdjacentMines(adjacentMines);
    }

    /**
     * Provides a string representation of the cell based on its current state.
     * If the cell is revealed, it displays the number of adjacent mines.
     * If the cell is flagged, it displays an "F".
     * Otherwise, it displays an empty string for a hidden, unflagged cell.
     * @return A string representing the cell's display state.
     */
    @Override
    public String display() {
        return isRevealed() ? String.valueOf(getAdjacentMines()) : (isFlagged() ? "F" : "");
    }
}
