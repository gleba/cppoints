package cpapp.view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 9:24
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class ClearAlertController {
    public Button cb;

    public void onClose(ActionEvent actionEvent) {
        Stage stage  = (Stage) cb.getScene().getWindow();
        stage.close();
    }
}
