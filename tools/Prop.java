package tools;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * Date: 09.04.13
 * Time: 11:30
 * When simplicity is raised in your mind, do not follow the thoughts of, rest in void.
 */

import javafx.beans.property.ReadOnlyDoubleProperty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Prop extends Properties {
    private String homeDir = new java.io.File("").getAbsolutePath();
    private String settingsFilename = homeDir + File.separator;// + "mySettings.properties";

    public Prop(String fileName) {
        if (instance == null)
            instance = this;
        settingsFilename += fileName;
        try {
            FileInputStream input = new FileInputStream(settingsFilename);
            load(input);
            input.close();
            System.out.println("OK: " + settingsFilename);
        } catch (Exception ignore) {
            System.out.println("file not found: " + settingsFilename);
            storeToFile();
        }
    }

    public void storeToFile() {
        try {
            FileOutputStream output = new FileOutputStream(settingsFilename);
            store(output, "User saved settings");
            output.close();
        } catch (Exception ignore) {
            System.out.println("writeParams ERROR: " + ignore.toString());
        }
    }

    private static Prop instance = new Prop("settings.properties");

    public static String getProp(String key) {
        return instance.getProperty(key);
    }

    public static void saveProp(String key, String value) {
        instance.setProperty(key, String.valueOf(value));
    }
    public static void saveProp(String key, int value) {
        instance.setProperty(key, String.valueOf(value));
    }
    public static void saveProp(String key, Double value) {
        instance.setProperty(key, Double.toString(value));
    }
    public static void saveProp(String key, ReadOnlyDoubleProperty value) {
        instance.setProperty(key, Double.toString(value.getValue()));
    }

    public static void write() {
        instance.storeToFile();
    }

    public static double getDouble(String key,Double ifnull) {
        String s = instance.getProperty(key);
        Double value = ifnull;
        if (s !=null){
            try {
                value = Double.parseDouble(s);
            } catch (Exception ex){
                S.log(ex.toString());
            }
        };
        return value;
    }

    public static int getInt(String key, int ifnull) {
        String s = instance.getProperty(key);
        int value = ifnull;
        if (s !=null){
            try {
                value = Integer.valueOf(s);
            } catch (Exception ex){
                S.log(ex.toString());
            }
        };
        return value;
    }
}
