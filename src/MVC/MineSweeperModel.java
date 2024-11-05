package MVC;

public class MineSweeperModel {
    private final int rows;
    private final int cols;
    private final int totalMines;
    private Cell[][] board;

    public MineSweeperModel(int rows, int cols, int totalMines) {
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        initializeBoard();
    }

    private void initializeBoard() {
        board = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell();
            }
        }
        placeMines();
        calculateAdjacentMines();
    }

    private void placeMines() {
        // Randomly place mines on the board
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int r = (int) (Math.random() * rows);
            int c = (int) (Math.random() * cols);
            if (!board[r][c].isMine()) {
                board[r][c].setMine(true);
                minesPlaced++;
            }
        }
    }

    private void calculateAdjacentMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!board[i][j].isMine()) {
                    board[i][j].setAdjacentMines(countAdjacentMines(i, j));
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
                if (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getTotalMines() {
        return totalMines;
    }
}
