package State;

import MVC.Model;
import Singleton.Singleton;

public class LostState implements GameState {
    @Override
    public void handleInput(Model model) {
        Singleton singleton = Singleton.getInstance();
        singleton.getView().showGameOver();
        singleton.getView().disableButtons();
    }

    @Override
    public String getStateName() {
        return "Lost";
    }
}