package MVC;

import java.util.ArrayList;
import java.util.List;

import Factory.EmptyCell;
import Factory.MineCell;
import Factory.NumberCell;
import Singleton.Singleton;
import Observer.Observer;
import Observer.ObserverNotification;

public class Model {
    private List<Observer> observers = new ArrayList<>();
    private ObserverNotification observerNotification = new ObserverNotification();

    public Model() {
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
        Singleton singleton = Singleton.getInstance();
        int rows = getRows();
        int cols = getCols();
        Cell[][] board = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new EmptyCell();
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
                getBoard()[r][c] = new MineCell();
                minesPlaced++;
            }
        }
    }

    private void calculateAdjacentMines() {
        Singleton singleton = Singleton.getInstance();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (!getBoard()[i][j].isMine()) {
                    getBoard()[i][j] = new NumberCell(countAdjacentMines(i, j));
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
        return Singleton.getInstance().getBoard();
    }

    public Cell getCell(int row, int col) {
        return Singleton.getInstance().getBoard()[row][col];
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
        return Singleton.getInstance().getRows();
    }

    public int getCols() {
        return Singleton.getInstance().getCols();
    }

    public int getTotalMines() {
        return Singleton.getInstance().getMines();
    }
}
