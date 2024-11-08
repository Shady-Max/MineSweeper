package MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Adapter.Leaderboard;
import Singleton.Singleton;

public class View extends JFrame {

    private final JLabel minesLabel;
    private final JLabel timeLabel;
    private final JLabel gameResultLabel;
    private JButton[][] buttons;
    private CellClickListener clickListener;
    private JPanel boardPanel;

    public View(int rows, int cols) {
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

        boardPanel = new JPanel(new GridLayout(rows, cols));
        addButtons(rows, cols);

        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void addButtons(int rows, int cols) {
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
    }

    public void setCellClickListener(CellClickListener listener) {
        this.clickListener = listener;
    }

    public void setFlag(int row, int col, boolean isFlagged) {
        JButton button = buttons[row][col];
        if (!button.isEnabled()) return;
        buttons[row][col].setText(isFlagged ? "F" : "");
    }

    public void revealCell(int row, int col, int adjacentMines) {
        JButton button = buttons[row][col];
        if (!button.isEnabled()) return;
        button.setEnabled(false);
        button.setText(adjacentMines > 0 ? String.valueOf(adjacentMines) : "");
    }

    public void showMine(int row, int col) {
        buttons[row][col].setText("X");
        buttons[row][col].setEnabled(false);
    }

    public void showGameOver() {
        disableButtons();
        gameResultLabel.setText("Game Over");
        int choice = JOptionPane.showOptionDialog(
                this, "Game Over! Would you like to restart?",
                "Game Over!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, new String[] {"Restart", "Exit"}, "Restart"
        );

        if (choice == JOptionPane.YES_OPTION) {
            restartGame();
        }
        else {
            System.exit(0);
        }
    }

    public void showWinMessage() {
        disableButtons();
        gameResultLabel.setText("You win!");
        int choice = JOptionPane.showOptionDialog(
                this, "You won! Would you like to restart?",
                "You Win!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, new String[] {"Restart", "Exit"}, "Restart"
        );

        if (choice == JOptionPane.YES_OPTION) {
            restartGame();
        }
        else {
            System.exit(0);
        }
    }

    public void disableButtons() {
        for (int i=0; i<buttons.length; i++) {
            for (int j=0; j<buttons[i].length; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public void setMinesCount(int count) {
        minesLabel.setText("Mines: " + count);
    }

    public void setTimeCount(int count) {
        timeLabel.setText("Time: " + count);
    }

    private void restartGame() {
        Singleton.getInstance().resetGame();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        gameResultLabel.setText("");
    }


}
