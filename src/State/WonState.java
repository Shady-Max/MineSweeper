package State;

import MVC.Model;
import Singleton.Singleton;

public class WonState implements GameState {
    @Override
    public void handleInput(Model model) {
        Singleton singleton = Singleton.getInstance();
        singleton.getView().showWinMessage();
        //singleton.getView().disableButtons();
    }

    @Override
    public String getStateName() {
        return "Won";
    }
}
