package cpapp.view;

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import tools.S;

import java.net.URL;
import java.util.ResourceBundle;

public class TextAreaLogController implements Initializable {
    public TextArea textArea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        S.setLogTextArea(textArea);
    }



}


