package MVC;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import Adapter.Leaderboard;
import Adapter.Score;

public class LeaderboardView extends JFrame {
    private Leaderboard leaderboard;

    public LeaderboardView(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
        setTitle("Leaderboard");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 + 210;                                  // 1920 / 2 = 960 + 220 =
        int y = (screenSize.height - getHeight()) / 2;                       // 1080 / 2 =               540
        setLocation(x, y);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // Make it non-editable
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));

        StringBuilder scoresText = new StringBuilder("Top Scores:\n\n");
        List<Score> topScores = leaderboard.getTopScores(10);
        for (Score score : topScores) {
            scoresText.append(score.toString()).append("\n");
        }
        textArea.setText(scoresText.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to display the leaderboard window
    public void showLeaderboardView() {
        setVisible(true);
    }

    public void closeLeaderboardView() {
        setVisible(false);
    }
}

