package cpapp.model.vo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created with IntelliJ IDEA.
 * User: YP
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class MPoint {
//    public String deviceId = "";
    public String index = "";
    public String id = "";
    public String name = "";
    public String status = "";


    public StringProperty indexProperty() { return new SimpleStringProperty(index); }
    public StringProperty idProperty() { return new SimpleStringProperty(id); }
    public StringProperty nameProperty() { return new SimpleStringProperty(name); }
    public StringProperty statusProperty() { return new SimpleStringProperty(status); }


    public MPoint(DPoint dp, CPoint cp) {
//        deviceId = dp.id;
        index = dp.index;
        id = cp.id;
        name = cp.name;
        status = "ok";
    }

    public MPoint(CPoint cp) {
        id = cp.id;
        name = cp.name;
        status = "fall";
    }

    public MPoint(DPoint dp) {
        id = dp.id;
        index = dp.index;
//        name = "н";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
