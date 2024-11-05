public class MineSweeperGameSingletone {
    private static MineSweeperGameSingletone instance;
    private final int rows;
    private final int cols;
    private final int mines;
    private Cell[][] board;
    private boolean gameLost;
    private boolean gameWon;

    // Приватный конструктор
    private MineSweeperGameSingletone(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        initializeBoard();
        placeMines();
        calculateNeighboringMines();
    }

    // Метод для получения единственного экземпляра
    public static MineSweeperGameSingletone getInstance(int rows, int cols, int mines) {
        if (instance == null) {
            instance = new MineSweeperGameSingletone(rows, cols, mines);
        }
        return instance;
    }

    // Метод инициализации доски
    private void initializeBoard() {
        board = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = new Cell();
            }
        }
    }

    // Метод для размещения мин случайным образом
    private void placeMines() {
        int placedMines = 0;
        while (placedMines < mines) {
            int randomRow = (int) (Math.random() * rows);
            int randomCol = (int) (Math.random() * cols);
            if (!board[randomRow][randomCol].hasMine()) {
                board[randomRow][randomCol].setMine(true);
                placedMines++;
            }
        }
    }

    // Метод для подсчета мин у соседних ячеек
    private void calculateNeighboringMines() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col].hasMine()) {
                    continue;
                }
                int mineCount = 0;
                for (int r = -1; r <= 1; r++) {
                    for (int c = -1; c <= 1; c++) {
                        int newRow = row + r;
                        int newCol = col + c;
                        if (isInBounds(newRow, newCol) && board[newRow][newCol].hasMine()) {
                            mineCount++;
                        }
                    }
                }
                board[row][col].setNeighboringMines(mineCount);
            }
        }
    }

    // Проверка на выход за границы доски
    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // Метод обработки нажатия на ячейку
    public void openCell(int row, int col) {
        if (!isInBounds(row, col) || board[row][col].isRevealed()) return;

        board[row][col].setRevealed(true);

        if (board[row][col].hasMine()) {
            gameLost = true;
            revealMines();
        } else if (board[row][col].getNeighboringMines() == 0) {
            revealAdjacentCells(row, col);
        }
        checkWinCondition();
    }

    // Метод для открытия соседних ячеек при отсутствии мин
    private void revealAdjacentCells(int row, int col) {
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                int newRow = row + r;
                int newCol = col + c;
                if (isInBounds(newRow, newCol) && !board[newRow][newCol].isRevealed()) {
                    openCell(newRow, newCol);
                }
            }
        }
    }

    // Метод для проверки условия победы
    private void checkWinCondition() {
        gameWon = true;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!board[row][col].hasMine() && !board[row][col].isRevealed()) {
                    gameWon = false;
                    return;
                }
            }
        }
    }

    // Метод для открытия всех мин при проигрыше
    private void revealMines() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col].hasMine()) {
                    board[row][col].setRevealed(true);
                }
            }
        }
    }

    // Методы для проверки состояния игры
    public boolean isGameLost() {
        return gameLost;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public Cell[][] getBoard() {
        return board;
    }
}
