package Singleton;

import MVC.Cell;
import MVC.Controller;
import MVC.Model;
import MVC.View;
import State.GameState;
import State.LostState;
import State.PlayingState;
import State.WonState;
import Observer.*;

import javax.swing.*;

public class Singleton {
    private static Singleton instance;
    private View view;
    private Model model;
    private Controller controller;
    private final int rows;
    private final int cols;
    private final int mines;
    private Cell[][] board;
    private boolean gameLost;
    private boolean gameWon;
    private GameState gameState;

    private Singleton(int rows, int cols, int mines) {
        gameState = new PlayingState();
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    private Singleton() {
        gameState = new PlayingState();
        rows = 10;
        cols = 10;
        mines = 10;
    }

    public static Singleton getInstance(int rows, int cols, int mines) {
        if (instance == null) {
            instance = new Singleton(rows, cols, mines);
        }
        return instance;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            return new Singleton();
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
        gameState = new WonState();
        gameState.handleInput(model);
        return true;
    }

    public void setGameLost() {
        gameLost = true;
        gameState = new LostState();
        gameState.handleInput(model);
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

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }

    public Singleton setController(Controller controller) {
        this.controller = controller;
        return this;
    }

    public Singleton setView(View view) {
        this.view = view;
        return this;
    }

    public Singleton setModel(Model model) {
        this.model = model;
        return this;
    }

    public void resetGame() {
        gameLost = false;
        gameWon = false;
        gameState = new PlayingState();
        model.initializeBoard();
        controller.startTimer();

        model.notifyObservers(new Cell());
    }
}
