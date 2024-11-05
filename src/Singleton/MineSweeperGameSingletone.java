package Singleton;

import MVC.Cell;

public class MineSweeperGameSingletone {
    private static MineSweeperGameSingletone instance;
    private final int rows;
    private final int cols;
    private final int mines;
    private Cell[][] board;
    private boolean gameLost;
    private boolean gameWon;

    private MineSweeperGameSingletone(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    public static MineSweeperGameSingletone getInstance(int rows, int cols, int mines) {
        if (instance == null) {
            instance = new MineSweeperGameSingletone(rows, cols, mines);
        }
        return instance;
    }

    public static MineSweeperGameSingletone getInstance() {
        if (instance == null) {
            return new MineSweeperGameSingletone(10, 10, 10);
        }
        return instance;
    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public boolean isWinConditionMet() {
        gameWon = true;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!board[row][col].isMine() && !board[row][col].isRevealed()) {
                    gameWon = false;
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameLost() {
        return gameLost;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getMines() {
        return mines;
    }
}
