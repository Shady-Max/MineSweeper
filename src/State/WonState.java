package State;

import MVC.MineSweeperModel;
import Singleton.MineSweeperGameSingletone;

public class WonState implements GameState {
    @Override
    public void handleInput(MineSweeperModel model) {
        MineSweeperGameSingletone singleton = MineSweeperGameSingletone.getInstance();

        singleton.getView().showWinMessage();

        singleton.getView().disableButtons();
    }

    @Override
    public String getStateName() {
        return "Won";
    }
}
