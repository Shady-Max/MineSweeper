package Proxy;

import MVC.Cell;

import MVC.Cell;
import State.CellState;

public class CellProxy extends Cell {
    private Cell realcell;
    private boolean isFlagged = false;

    public CellProxy(Cell realcell) {
        this.realcell = realcell;
    }

    @Override
    public void reveal() {
        if (!isFlagged && !realcell.isRevealed()) {
            realcell.reveal();
            System.out.println("CellProxy: Revealing cell of type " + realcell.getClass().getSimpleName());
        }
        else {
            System.out.println("CellProxy: Cannot reveal flagged or already revealed cell");
        }
    }

    @Override
    public void toggleFlag() {
        isFlagged = !isFlagged;
        System.out.println("CellProxy: Flag status changed to " + isFlagged);
    }
    @Override
    public String display() {
        return realcell.display();
    }

    @Override
    public boolean isMine() { return realcell.isMine(); }
    @Override
    public void setMine(boolean isMine) { realcell.setMine(isMine); }

    @Override
    public boolean isRevealed() { return realcell.isRevealed(); }
    @Override
    public void setRevealed(boolean isRevealed) { realcell.setRevealed(isRevealed); }

    @Override
    public boolean isFlagged() { return realcell.isFlagged(); }
    @Override
    public void setFlagged(boolean isFlagged) { realcell.setFlagged(isFlagged); }

    @Override
    public int getAdjacentMines() { return realcell.getAdjacentMines(); }
    @Override
    public void setAdjacentMines(int adjacentMines) { realcell.setAdjacentMines(adjacentMines); }

    @Override
    public void setState(CellState state) {
        realcell.setState(state);
    }

    @Override
    public Cell getCell() {
        return realcell;
    }
}
