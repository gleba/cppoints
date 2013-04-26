package tools.view;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import cpapp.AppCore;
import cpapp.SwingApp;
import cpapp.view.mapfx.JFXMap;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import tools.S;
import tools.events.SEvents;
import tools.events.SListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.*;
import java.util.Timer;

/**
 * Created with IntelliJ IDEA.
 * User: YP
 * D/T: 8:17 / 26.04.13
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class SwingContainerFX {
    private JDialog dialog;
    private JPanel panel;
    private Pane pane;
    private Scene scene;
    private Boolean started=false;

    public SwingContainerFX(JPanel panel) {
        this.panel = panel;
    }

    public void visible(Boolean value){
        dialog.setVisible(value);
        if (value&&started)
            updateSize();
    }
    public void placeUpOnContainer(Pane container) {
        this.pane = container;
        dialog = new JDialog(AppCore.frame);
        dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dialog.add(panel);
        dialog.setResizable(false);
        dialog.setUndecorated(true);
        dialog.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lastX = 100;//Integer.getInteger(Double.toString(pane.getBoundsInParent()));
                lastY = 100;//Integer.getInteger(Double.toString(pane.getLayoutY()));
                lastH = 100;//Integer.getInteger(Double.toString(pane.getHeight()));
                lastW = 100;//Integer.getInteger(Double.toString(pane.getWidth()));
                scene = pane.getScene();
                updateSize();
                started = true;
            }
        });
        AppCore.frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                resetTimer();
                updateSize();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                resetTimer() ;
                updateSize();
            }

            @Override
            public void componentShown(ComponentEvent e) {
                resetTimer();
                updateSize();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                resetTimer() ;
                updateSize();
            }
        });
    }
    private Integer lastW,lastH,lastX,lastY;
    private Rectangle r;
    private void updateSize() {


        here:{
        if (task==null){
            task = new TimerTask() {
                public void run()
                {
                    count++;
                    if (count>countTimer){
                        resetTimer();
                    } else {
                        updateSize();
                    }
                }
            };
            timer = new Timer();
            timer.scheduleAtFixedRate(task,100,20);
        }

        if (dialog.isVisible()){
            final Point2D windowCoord = new Point2D(scene.getWindow().getX(), scene.getWindow().getY());
            final Point2D sceneCoord = new Point2D(scene.getX(), scene.getY());
            final Point2D nodeCoord = pane.localToScene(0.0, 0.0);
            lastX = (int) Math.round(windowCoord.getX() + sceneCoord.getX() + nodeCoord.getX());
            lastY = (int) Math.round(windowCoord.getY() + sceneCoord.getY() + nodeCoord.getY());
            Double ph = pane.getHeight();
            Double pw = pane.getWidth();
            lastH = ph.intValue();//Integer.getInteger(Double.toString(pane.getHeight()));
            lastW = pw.intValue();//Integer.getInteger(Double.toString(pane.getWidth()));
            Rectangle rn = new Rectangle(lastX,lastY,lastW,lastH);
            if(!rn.equals(r)){
                r = rn;
//                dialog.validate();
                panel.setBounds(0,0,r.width,r.height);
                dialog.setBounds(r);
//                dialog.doLayout();
//                System.out.println("new Rectangle "+r+ " " +dialog.getBounds() + " p"+ panel.getBounds());
            }
        }
        }
    }
    private long count=0;
    private long countTimer=18;
    java.util.Timer timer;
    TimerTask task;
    private void resetTimer() {
        count = 0;
        if (timer!=null)
            timer.cancel();
        if (task!=null)
            task.cancel();
        timer = null;
        task = null;
    }
}
