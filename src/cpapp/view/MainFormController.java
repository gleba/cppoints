package cpapp.view;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    public WebView wForm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wForm.getEngine().load("http://in-soul.ru/o/cppoint");
    }

    public void onEditClose(Event event) {
    }

    public void onEditChange(Event event) {

        Tab tab = (Tab) event.getTarget();
//        JFXMap.getInstance().swingView.visible(tab.isSelected());
    }
}
