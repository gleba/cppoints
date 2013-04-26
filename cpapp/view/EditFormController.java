package cpapp.view;

import cpapp.view.mapfx.JFXMap;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.HBox;
//import javafx.ext.swing.SwingComponent;


/**
 * Created with IntelliJ IDEA.
 * User: YP
 * D/T: 11:53 / 25.04.13
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class EditFormController implements Initializable {
    public HBox mapBox;

    private JFXMap map;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("started ok");
        map = JFXMap.getInstance();
        map.startOn(mapBox);
    }
}
