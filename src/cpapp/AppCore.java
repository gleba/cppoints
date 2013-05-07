package cpapp;

import cpapp.model.CommandListiner;
import cpapp.model.DataService;
import cpapp.model.SerialPortsService;
import tools.Prop;
//import tools.events.Bus;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * Date: 09.04.13
 * Time: 6:07
 */

public class AppCore {



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


