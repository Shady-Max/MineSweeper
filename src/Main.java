import MVC.MineSweeperController;
import MVC.MineSweeperModel;
import MVC.MineSweeperView;
import Singleton.MineSweeperGameSingletone;

public class Main {
    public static void main(String[] args) {
        int rows = 10;
        int cols = 10;
        int mines = 10;

        MineSweeperGameSingletone.getInstance(rows, cols, mines);

        MineSweeperModel model = new MineSweeperModel();
        MineSweeperView view = new MineSweeperView(rows, cols);

        MineSweeperGameSingletone.getInstance()
                .setModel(model)
                .setView(view)
                .setController(new MineSweeperController(model, view));
    }
}