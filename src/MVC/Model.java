package MVC;

import java.util.ArrayList;
import java.util.List;

import Factory.CellFactory;
import Factory.EmptyCell;
import Factory.MineCell;
import Factory.NumberCell;
import Singleton.Singleton;

public class Model {

    public Model() {
        initializeBoard();
    }

    public void initializeBoard() {
        Singleton singleton = Singleton.getInstance();
        int rows = getRows();
        int cols = getCols();
        Cell[][] board = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = CellFactory.createCell(false, 0);
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
                getBoard()[r][c] = CellFactory.createCell(true, 0);
                minesPlaced++;
            }
        }
    }

    private void calculateAdjacentMines() {
        Singleton singleton = Singleton.getInstance();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (!getBoard()[i][j].isMine()) {
                    getBoard()[i][j] = CellFactory.createCell(false, countAdjacentMines(i, j));
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
                if (getBoard()[i][j].getCell() == cell.getCell()) {
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
