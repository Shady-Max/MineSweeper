package Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Currently doesn't implemented without connection to other pattern. (Should add it later)
 */
public class CellGroup implements Component {
    private List<Component> components = new ArrayList<>();

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    public void reveal() {
        for (Component component : components) {
            component.reveal();
        }
    }

    @Override
    public void toggleFlag() {
        for (Component component : components) {
            component.toggleFlag();
        }
    }
}
