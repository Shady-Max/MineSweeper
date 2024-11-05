package MVC;

import java.util.ArrayList;
import java.util.List;
import Singleton.MineSweeperGameSingletone;
import Observer.Observer;
import Observer.ObserverNotification;

public class MineSweeperModel {
    private List<Observer> observers = new ArrayList<>();
    private ObserverNotification observerNotification = new ObserverNotification();

    public MineSweeperModel() {
        initializeBoard();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
        observerNotification.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
        observerNotification.removeObserver(observer);
    }

    public void notifyObservers(Cell cell) {
        observerNotification.notifyObservers(cell);
    }

    private void initializeBoard() {
        MineSweeperGameSingletone singleton = MineSweeperGameSingletone.getInstance();
        int rows = getRows();
        int cols = getCols();
        Cell[][] board = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell();
            }
        }
        singleton.setBoard(board);
        placeMines();
        calculateAdjacentMines();
    }

    private void placeMines() {
        int minesPlaced = 0;
        while (minesPlaced < getTotalMines()) {
            int r = (int) (Math.random() * getRows());
            int c = (int) (Math.random() * getCols());
            if (!getBoard()[r][c].isMine()) {
                getBoard()[r][c].setMine(true);
                minesPlaced++;
            }
        }
    }

    private void calculateAdjacentMines() {
        MineSweeperGameSingletone singleton = MineSweeperGameSingletone.getInstance();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (!getBoard()[i][j].isMine()) {
                    getBoard()[i][j].setAdjacentMines(countAdjacentMines(i, j));
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r < getRows() && c >= 0 && c < getCols() && getBoard()[r][c].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public Cell[][] getBoard() {
        return MineSweeperGameSingletone.getInstance().getBoard();
    }

    public Cell getCell(int row, int col) {
        return MineSweeperGameSingletone.getInstance().getBoard()[row][col];
    }

    public Pair getCell (Cell cell) {
        for (int i=0; i<getRows(); i++) {
            for (int j=0; j<getCols(); j++) {
                if (getBoard()[i][j] == cell) {
                    return new Pair(i, j);
                }
            }
        }
        return null;
    }

    public int getRows() {
        return MineSweeperGameSingletone.getInstance().getRows();
    }

    public int getCols() {
        return MineSweeperGameSingletone.getInstance().getCols();
    }

    public int getTotalMines() {
        return MineSweeperGameSingletone.getInstance().getMines();
    }
}
