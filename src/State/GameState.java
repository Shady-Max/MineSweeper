package State;

import MVC.MineSweeperModel;

public interface GameState {
    void handleInput(MineSweeperModel model);
    String getStateName();
}
