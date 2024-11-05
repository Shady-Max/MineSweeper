package Observer;

import java.util.List;
import MVC.*;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Cell cell);
}
