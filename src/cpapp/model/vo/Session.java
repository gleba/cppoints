package cpapp.model.vo;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 6:39
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class Session {
    public String id;
    public long startTime;
    public ArrayList<StoredCommand> commands;
    public ArrayList<DPoint> devicePoints;

    public Session(){
        commands = new ArrayList<StoredCommand>();
        devicePoints = new ArrayList<DPoint>();
        id = UUID.randomUUID().toString();
    }
}
