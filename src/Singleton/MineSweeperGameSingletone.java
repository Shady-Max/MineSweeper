package Singleton;

import MVC.Cell;
import MVC.MineSweeperController;
import MVC.MineSweeperModel
import MVC.MineSweeperView;

public class MineSweeperGameSingletone {
    private static MineSweeperGameSingletone instance;
    private MineSweeperView view;
    private MineSweeperModel model;
    private MineSweeperController controller;
    private final int rows;
    private final int cols;
    private final int mines;
    private Cell[][] board;
    private boolean gameLost;
    private boolean gameWon;
    private MineSweeperView view;

    private MineSweeperGameSingletone(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    private MineSweeperGameSingletone() {
        rows = 10;
        cols = 10;
        mines = 10;
    }

    public static MineSweeperGameSingletone getInstance(int rows, int cols, int mines) {
        if (instance == null) {
            instance = new MineSweeperGameSingletone(rows, cols, mines);
        }
        return instance;
    }

    public static MineSweeperGameSingletone getInstance() {
        if (instance == null) {
            return new MineSweeperGameSingletone();
        }
        return instance;
    }

    public void setView(MineSweeperView view) {
        this.view = view;
    }

    // Add a method to get the view
    public MineSweeperView getView() {
        return view;
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

    public void setGameLost() {
        gameLost = true;
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

    public MineSweeperModel getModel() {
        return model;
    }

    public MineSweeperView getView() {
        return view;
    }

    public MineSweeperController getController() {
        return controller;
    }

    public MineSweeperGameSingletone setController(MineSweeperController controller) {
        this.controller = controller;
        return this;
    }

    public MineSweeperGameSingletone setView(MineSweeperView view) {
        this.view = view;
        return this;
    }

    public MineSweeperGameSingletone setModel(MineSweeperModel model) {
        this.model = model;
        return this;
    }
}
