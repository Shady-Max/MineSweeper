import MVC.MineSweeperController;
import MVC.MineSweeperModel;
import MVC.MineSweeperView;

public class Main {
    public static void main(String[] args) {
        int rows = 10;
        int cols = 10;
        int mines = 10;

        MineSweeperModel model = new MineSweeperModel(rows,cols,mines);
        MineSweeperView view = new MineSweeperView(rows,cols);
        MineSweeperController controller = new MineSweeperController(model, view);
    }
}