package cpapp.model;

import tools.events.SEvents;
import tools.events.SListener;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 1:57
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class CommandListiner {
    public String lastCommandCode = "-1";
    public CommandListiner(){
        SEvents.only.listen(new SListener(SListener.OPENCLOSE){
            @Override
            public void run(Object o){
                lastCommandCode = "-1";
                if(!(Boolean)o){
                    CommandlLogic.endSession();
                }
            }
        });

        SEvents.only.listen(new SListener(SListener.ONSEND){
            @Override
            public void run(Object o){
                lastCommandCode = String.valueOf(o);
            }
        });

        SEvents.only.listen(new SListener(SListener.ONDATA){
            @Override
            public void run(Object o){
                String message = String.valueOf(o);
                CommandlLogic.newCommand(lastCommandCode, message);
            }
        });
    }

    public static CommandListiner instance;
    public static void start(){
        if(instance==null)
        instance = new CommandListiner();
    }
}
