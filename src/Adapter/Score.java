package Adapter;

public class Score {
    private String playerName;
    private int completionTime;  // In milliseconds

    public Score(String playerName, int completionTime) {
        this.playerName = playerName;
        this.completionTime = completionTime;
    }

    // Getters
    public String getPlayerName() { return playerName; }
    public int getCompletionTime() { return completionTime; }

    @Override
    public String toString() {
        return playerName + " Time: " + completionTime + " sec";
    }
}

