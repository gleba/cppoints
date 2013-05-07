package cpapp.view;

import cpapp.AppCore;
import cpapp.model.CommandlLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tools.S;
import tools.events.SEvents;
import tools.events.SListener;

import java.net.URL;
import java.util.ResourceBundle;

public class ReadFormController implements Initializable {
    public ComboBox cbPorts;
    public TextArea logArea;
    public Button btOpen;
    public CheckBox cbAutoRead;
    public Button btRead;
    public Button btClear;
    public Button btControl;


    public void onComPortsShow(Event event) {
        bindPorts();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        S.setLogTextArea(logArea);
        bindPorts();
        SEvents.only.listen(new SListener(SListener.OPENCLOSE) {
            @Override
            public void run(Object object) {
                Boolean b = (Boolean) object;
                if (b) {
                    btOpen.setText("Закрыть");
                    btRead.setDisable(false);
                    btClear.setDisable(false);
                    btControl.setDisable(false);
                } else {
                    btOpen.setText("Открыть");
                    btRead.setDisable(true);
                    btClear.setDisable(true);
                    btControl.setDisable(true);
                }
            }
        });
    }

    private ObservableList portList;

    public void bindPorts() {
        portList = FXCollections.observableArrayList(AppCore.ports.avaliableList);
        AppCore.ports.checkPors();
        cbPorts.setPromptText(AppCore.ports.selectedPort);
        S.log("Доступные порты: " + portList);
        try {
            cbPorts.setItems(portList);
        } catch (Exception er){
            System.out.println("cbPorts.setItems("+portList+"); " +er );
        }

    }

    public void cbPortsAction(ActionEvent actionEvent) {
        AppCore.ports.setPort(cbPorts.getSelectionModel().getSelectedItem().toString());
    }

    public void openAction(ActionEvent actionEvent) {
        if (AppCore.ports.open){
            AppCore.ports.closePort();
        } else {
            AppCore.ports.openPort();
        }
    }

    public void readAction(ActionEvent actionEvent) {
        AppCore.ports.sendString("1");
    }

    public void clearAction(ActionEvent actionEvent) {
        final Stage dialogStage = new Stage();
        Button bt = new Button("Отчистить");
        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                AppCore.ports.sendString("0");
                dialogStage.close();
            }});
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text("Эта комманда отчистит все записи на устройстве"), bt).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();
    }

    public void controlAction(ActionEvent actionEvent) {
        AppCore.ports.sendString("7");
    }

    public void simulate(ActionEvent actionEvent) {
//        CommandListiner.instance.lastCommandCode = "1";
//        SEvents.only.call(SListener.ONDATA,"12 47566\n13 47566");
        CommandlLogic.newCommand("1","1 47566\n2 4561");
//        AppCore.data.fullDemoTrack();
    }
}
