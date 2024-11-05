package State;

import MVC.Model;

public interface GameState {
    void handleInput(Model model);
    String getStateName();
}
