package State;

import MVC.Model;
import Singleton.Singleton;

public class PlayingState implements GameState {
    @Override
    public void handleInput(Model model) {
        Singleton singleton = Singleton.getInstance();
    }

    @Override
    public String getStateName() {
        return "Playing";
    }


}
