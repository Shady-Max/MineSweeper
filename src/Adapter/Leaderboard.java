package Adapter;

import java.util.List;

public interface Leaderboard {
    void addScore(Score score);
    List<Score> getTopScores(int limit);
    void displayScores();
    void closeScores();
}

