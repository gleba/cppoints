package tools;

import javafx.scene.control.TextArea;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * Date: 11.04.13
 * Time: 4:27
 * Live by the code. Die by the code. ©The Way of the Samurai.
 */
public class S {

    public static void log(Object text){
        String s = text.toString();
        System.out.println(s);

        s = "\n"+timestamp("HH:mm:ss") + ": "+s;
        logText.add(s);
        if (logArea!=null)
            logArea.appendText(s);
    }

    public static void trace(Object text){
        String s = text.toString();
//        System.out.println(s);
        s = "\n"+s;
        logText.add(s);
        if (logArea!=null)
            logArea.appendText(s);
    }

    private static TextArea logArea;
    private static ArrayList<String> logText = new ArrayList<String>();
    public static void setLogTextArea(TextArea ta){
        System.out.print("setLogTextArea "+ta);
        logArea = ta;
        logArea.clear();
        for (String s :logText){
            logArea.appendText(s);
        }
    }

    public static String timestamp(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());

    }
//    System.out.println(DateUtils.now("dd MMMMM yyyy"));
//    System.out.println(DateUtils.now("yyyyMMdd"));
//    System.out.println(DateUtils.now("dd.MM.yy"));
//    System.out.println(DateUtils.now("MM/dd/yy"));
//    System.out.println(DateUtils.now("yyyy.MM.dd G 'at' hh:mm:ss z"));
//    System.out.println(DateUtils.now("EEE, MMM d, ''yy"));
//    System.out.println(DateUtils.now("h:mm a"));
//    System.out.println(DateUtils.now("H:mm:ss:SSS"));
//    System.out.println(DateUtils.now("K:mm a,z"));
//    System.out.println(DateUtils.now("yyyy.MMMMM.dd GGG hh:mm aaa"));
}
