package MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MineSweeperView extends JFrame {

    private final JLabel minesLabel;
    private final JLabel timeLabel;
    private final JLabel gameResultLabel;
    private final JButton[][] buttons;
    private CellClickListener clickListener;

    public MineSweeperView(int rows, int cols) {
        setTitle("MineSweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(450,450);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel statusPanel = new JPanel();
        minesLabel = new JLabel("Mines: 0");
        timeLabel = new JLabel("Time: 0");
        gameResultLabel = new JLabel("");
        statusPanel.add(minesLabel);
        statusPanel.add(timeLabel);
        statusPanel.add(gameResultLabel);

        JPanel boardPanel = new JPanel(new GridLayout(rows, cols));
        buttons = new JButton[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                boardPanel.add(button);
                buttons[i][j] = button;
                int r = i, c = j;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (clickListener != null) clickListener.onCellClick(r, c, false);
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            if (clickListener != null) clickListener.onCellClick(r, c, true);
                        }
                    }
                });
            }
        }

        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    public void setCellClickListener(CellClickListener listener) {
        this.clickListener = listener;
    }

    public void setFlag(int row, int col, boolean isFlagged) {
        buttons[row][col].setText(isFlagged ? "F" : "");
    }

    public void revealCell(int row, int col, int adjacentMines) {
        JButton button = buttons[row][col];
        button.setEnabled(false);
        button.setText(adjacentMines > 0 ? String.valueOf(adjacentMines) : "");
    }

    public void showMine(int row, int col) {
        buttons[row][col].setText("X");
        buttons[row][col].setEnabled(false);
    }

    public void showGameOver() {
        gameResultLabel.setText("Game Over");
        JOptionPane.showMessageDialog(this, "Game Over!");
    }

    public void showWinMessage() {
        gameResultLabel.setText("You win!");
        JOptionPane.showMessageDialog(this, "You Win!");
    }

    public void setMinesCount(int count) {
        minesLabel.setText("Mines: " + count);
    }

    public void setTimeCount(int count) {
        timeLabel.setText("Time: " + count);
    }


}
