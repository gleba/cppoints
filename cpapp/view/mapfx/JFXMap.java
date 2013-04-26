package cpapp.view.mapfx;

import javafx.application.Platform; 
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.swing.*;
import javafx.beans.value.ChangeListener;
import tools.view.SwingContainerFX;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: YP
 * D/T: 18:46 / 25.04.13
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class JFXMap {
    private static JFXMap _instance;
    public static JFXMap getInstance(){
        if (_instance==null)
            _instance=new JFXMap();
        return _instance;
    }

    static public JXMapController jxmap;
    public SwingContainerFX swingView;
    public JFXMap(){
        jxmap = new JXMapController();
        jxmap.bindMouse();
    }



    public Pane container;
    public void startOn(HBox mapBox) {
        container = mapBox;
        swingView = new SwingContainerFX(jxmap);
        swingView.placeUpOnContainer(container);

    }

}
