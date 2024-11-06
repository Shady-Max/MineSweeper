package Adapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import Singleton.Singleton;

public class LeaderboardAdapter implements Leaderboard {
    private List<Score> scores = new ArrayList<>();

    @Override
    public void addScore(Score score) {
        scores.add(score);
        scores.sort(Comparator.comparingInt(Score::getCompletionTime).reversed());
    }

    @Override
    public List<Score> getTopScores(int limit) {
        return scores.subList(0, Math.min(limit, scores.size()));
    }

    @Override
    public void displayScores() {
        Singleton.getInstance().showLeaderboard();
    }

    @Override
    public void closeScores() {
        Singleton.getInstance().closeLeaderboard();
    }
}
