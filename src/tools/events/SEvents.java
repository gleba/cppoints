package tools.events;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 11.04.13 / 11:42
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class SEvents {

    private ArrayList<SListener> listeners = new ArrayList<SListener>();

    public void listen(SListener listener){
        listeners.add(listener);
    }

    public void unMap(SListener listener){
        listeners.remove(listener);
    }

    public void call(String type, Object object){
        for (SListener listener : listeners){
            if (listener.type.equals(type))
                listener.run(object);
        }
    }

    public static SEvents only = new SEvents();

    public void call(String datachange) {
       call(datachange,null);
    }
}
