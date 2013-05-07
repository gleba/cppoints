package cpapp.view;

import cpapp.AppCore;
import cpapp.model.DataService;
import cpapp.model.vo.CPTrack;
import cpapp.model.vo.CPoint;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.events.SEvents;
import tools.events.SListener;

import java.net.URL;
import java.util.ResourceBundle;
//import javafx.ext.swing.SwingComponent;


/**
 * Created with IntelliJ IDEA.
 * User: YP
 * D/T: 11:53 / 25.04.13
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class EditFormController implements Initializable {



    @FXML public TextField fileName;

    @FXML public TableView tableFiles;
    @FXML public TableColumn cFileName;
    @FXML public TableColumn cCreateTime;

    private DataService data;
    private CPTrack cpTrack;
    private int selectedIndex;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("started ok");
        data =AppCore.data;
        cCreateTime.setCellValueFactory(new PropertyValueFactory<CPTrack,String>("time"));
        cFileName.setCellValueFactory(new PropertyValueFactory<CPTrack, String>("name"));
        SEvents.only.listen(new SListener(SListener.CPLIST_CHANGE) {
            @Override
            public void run(Object o) {
                initTableFileList();
            }
        });
        SEvents.only.listen(new SListener(SListener.CP_CHANGE) {
            @Override
            public void run(Object o) {
                initSelection();
            }
        });

        tableFiles.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object o2) {
//                System.out.println(observableValue+","+o+","+o2);
                Object obj = null;
                if (o!=null)
                    obj = o;
                if (o2!=null)
                    obj = o2;

                if(obj!=null){
                    cpTrack = (CPTrack) obj;
                    data.setCPTrack(cpTrack);
                    fileName.setText(cpTrack.name);
                }
            }
        });
        initTableFileList();
    }

    ObservableList<CPTrack> olist;
    public void addFile(ActionEvent actionEvent) {
        String trackName = fileName.getText();
        data.addTrack(new CPTrack(trackName));
        if (data.cpTracks.size()>0){
            tableFiles.getSelectionModel().select(data.cpTracks.size()-1);
        }
    }

    private void initSelection(){
        if (data.cpTrack!=null)
            for (CPTrack cp : olist){
                if (cp.id.equals(data.cpTrack.id)){
                    tableFiles.getSelectionModel().select(cp);
                }
            }
    }
    private void initTableFileList() {
        if (!data.cpTracks.isEmpty()){
            olist = FXCollections.observableList(data.cpTracks);
            tableFiles.setItems(null);
            tableFiles.layout();
            tableFiles.setItems(olist);
            initSelection();
        } else {
            tableFiles.setItems(null);
            tableFiles.layout();
            SEvents.only.call(SListener.CP_CHANGE);
        }
    }

    public void remFile(ActionEvent actionEvent) {
        if (cpTrack!=null){
            data.remTrack(cpTrack);
            cpTrack = null;
            fileName.setText("");
        }
        if (data.cpTracks.size()>0){
            tableFiles.getSelectionModel().select(data.cpTracks.size()-1);
        }
    }

    public void addCPoint(ActionEvent actionEvent) {
        cpTrack.addCPoint(new CPoint("0","new CheckPoint"));
        SEvents.only.call(SListener.CP_CHANGE);

    }

    public void update(ActionEvent actionEvent) {
        if(cpTrack!=null){
            String trackName = fileName.getText();
            cpTrack.name = trackName;
            data.cpTrack.name = trackName;
            data.storage.write();
            initTableFileList();
        }
    }

    public void remCPoint(ActionEvent actionEvent) {

    }
}
