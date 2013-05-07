package cpapp.model;

//import soulways.model.vo.Session;

import cpapp.AppCore;
import cpapp.model.vo.DPoint;
import cpapp.model.vo.Session;
import cpapp.model.vo.StoredCommand;
import tools.events.SEvents;
import tools.events.SListener;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 6:35
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class CommandlLogic {

    private static DataService data = AppCore.data;

    public static void newCommand(String commandCode, String message) {
        StoredCommand command = new StoredCommand(commandCode,message);
        switch (commandCode){
            case "-1":
                data.session = new Session();
                data.session.startTime = Calendar.getInstance().getTimeInMillis();
//                data.session = session;
                break;
            case "1":
                String[] s1 = message.split("\n");
                for (String s : s1){
                    String[] s2 = s.split(" ");
                    DPoint scp = new DPoint(s2[0],s2[1]);
                    data.session.devicePoints.add(scp);
                }
                AppCore.data.mapTrack();
                break;
        }
        System.out.println("commandCode "+commandCode+" "+data.session);
        data.session.commands.add(command);
        SEvents.only.call(SListener.DATACHANGE);
    }

    public static void endSession() {
//        data.write();
    }
}

