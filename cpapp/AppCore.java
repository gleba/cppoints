package cpapp;

import cpapp.model.CommandListiner;
import cpapp.model.DataService;
import cpapp.model.SerialPortsService;
import javax.swing.JFrame;
import tools.Prop;
import tools.S;
//import tools.events.Bus;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * Date: 09.04.13
 * Time: 6:07
 */

public class AppCore {
    public static JFrame frame;

    public AppCore() {

    }

    public static SerialPortsService ports;
    public static Prop params;
    public static DataService data = new DataService();


    public static void init() {
        CommandListiner.start();
        params = new Prop("settings.properties");
        ports = new SerialPortsService();


    }


}


