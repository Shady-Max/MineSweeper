package MVC;

@FunctionalInterface
interface CellClickListener {
    void onCellClick(int row, int col, boolean isFlagged);
}