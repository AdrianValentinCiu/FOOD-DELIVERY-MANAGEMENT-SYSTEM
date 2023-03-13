package BLL;

import java.util.ArrayList;

public abstract class Observable {
    public abstract void registerObserver(Observer o);
    public abstract void notifyObservers(ArrayList<MenuItem> menuItem);
}
