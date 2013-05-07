package cpapp.model.vo;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 10:47
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * CheckPoint
 */
public class CPoint {
    public String id;
    public String name;
    public CPoint(String id, String name) {
        this.id = id;
        this.name = name;
    }



//    public StringProperty indexProperty() { return new SimpleStringProperty(index); }
    public StringProperty idProperty() { return new SimpleStringProperty(id); }
    public StringProperty nameProperty() { return new SimpleStringProperty(name); }
//    public StringProperty statusProperty() { return new SimpleStringProperty(status); }


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
