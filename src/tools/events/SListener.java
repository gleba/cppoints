package tools.events;

import java.util.EventListener;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 11.04.13 / 12:22
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class SListener implements EventListener {
    public static final String DATACHANGE = "data_change";
    public static final String OPENCLOSE = "on_change";
    public static final String ONDATA = "on_data";
    public static final String ONSEND = "on_send";
    public static final String NEWPOINT = "new_point";
    public static final String CPLIST_CHANGE = "add_cpfile";
    public static final String CP_CHANGE = "cp_change";

    public void run(Object objec) {

    }

    public String type;
    public SListener(String type){
        this.type = type;
    }



    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
