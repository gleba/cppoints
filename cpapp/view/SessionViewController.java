package cpapp.view;

import cpapp.AppCore;
import cpapp.model.DataService;
import cpapp.model.vo.MPoint;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.events.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 10:13
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class SessionViewController implements Initializable {

//    public TableView tbTrack;

    @FXML private TableView tableView;
    @FXML private TableColumn<MPoint,String> cellStatus;
    @FXML private TableColumn<MPoint,String> cellId;
    @FXML private TableColumn<MPoint,String> cellName;
    @FXML private TableColumn<MPoint,String> cellIndex;

    private DataService data;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = AppCore.data;
        cellId.setCellValueFactory(new PropertyValueFactory<MPoint,String>("id"));
        cellIndex.setCellValueFactory(new PropertyValueFactory<MPoint,String>("index"));
        cellName.setCellValueFactory(new PropertyValueFactory<MPoint, String>("name"));
        cellStatus.setCellValueFactory(new PropertyValueFactory<MPoint,String>("status"));
        SEvents.only.listen(new SListener(SListener.DATACHANGE) {
            @Override
            public void run(Object o) {
                ObservableList<MPoint> olist = FXCollections.observableList(data.mapped);
                tableView.setItems(olist);
            }

        });
    }
}
