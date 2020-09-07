package tower;

import java.util.ArrayList;
import vehicles.Flyable;

public class Tower {
    private ArrayList<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable){
        if (observers.contains(flyable))
            return;
        observers.add(flyable);
    }

    public void unregister(Flyable flyable){
        observers.remove(flyable);
    }

    protected void conditionsChanged(){
        for (int x = 0; x < observers.size(); x++){
            observers.get(x).updateConditions();
        }
    }
}