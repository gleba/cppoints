/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpapp;
 

import cpapp.AppCore;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import tools.Prop;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author YP
 */
public class JavaFXSwingApplication1 extends JApplet {
    
    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer; 
    public static JFrame frame;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }

                frame = new JFrame("JavaFX 2 in Swing!!!");
                AppCore.frame = frame;
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JApplet applet = new JavaFXSwingApplication1();
                applet.init();

                frame.setContentPane(applet.getContentPane());

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                Rectangle r = new Rectangle(Prop.getInt("stageX", 100),Prop.getInt("stageY", 100),Prop.getInt("stageW", 700),Prop.getInt("stageH", 500));
                frame.setMinimumSize(new Dimension(600,500));
                frame.setBounds(r);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        Prop.saveProp("stageH", frame.getHeight());
                        Prop.saveProp("stageW", frame.getWidth());
                        Prop.saveProp("stageX", frame.getX());
                        Prop.saveProp("stageY", frame.getY());
                        Prop.write(); 
                        super.windowClosing(e);
                    }
                });
                applet.start();
            }
        });
    }

    @Override
    public void init() {
        fxContainer = new JFXPanel();
        add(fxContainer, BorderLayout.CENTER);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createScene();
            }
        });
    }

    static public Scene scene;
    private void createScene() {
        AppCore.init();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/fxml/mainform.fxml"));
            scene = new Scene(root);
            fxContainer.setScene(scene);
        } catch (Exception er){
            System.out.println(er);
        };

    }
}
