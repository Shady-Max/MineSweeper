package Observer;

import java.util.List;
import java.util.ArrayList;
import MVC.Cell;

public class ObserverNotification implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Cell cell) {
        for (Observer observer : observers) {
            observer.update(cell);
        }
    }
}
