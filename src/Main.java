import MVC.Controller;
import MVC.Model;
import MVC.View;
import Singleton.Singleton;

public class Main {
    public static void main(String[] args) {
        int rows = 10;
        int cols = 10;
        int mines = 10;

        Singleton.getInstance(rows, cols, mines);

        Model model = new Model();
        View view = new View(rows, cols);

        Singleton.getInstance()
                .setModel(model)
                .setView(view)
                .setController(new Controller(model, view));
    }
}