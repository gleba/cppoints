package cpapp.view;

/**
 * Created with IntelliJ IDEA.
 * User: YP
 * D/T: 18:53 / 26.04.13
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */

import cpapp.AppCore;
import cpapp.model.DataService;
import cpapp.model.vo.MPoint;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTableController implements Initializable {

    @FXML public TableView tableView;
    @FXML public TableColumn cellName;
    @FXML public TableColumn cellId;
    private DataService data;
    private ObservableList<MPoint> olist;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        data = AppCore.data;

        cellName.setCellValueFactory(new PropertyValueFactory("name"));
        cellId.setCellValueFactory(new PropertyValueFactory("id"));
        //Set cell factory for cells that allow editing
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {

                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };
        cellName.setCellFactory(cellFactory);
        cellId.setCellFactory(cellFactory);

        //Set handler to update ObservableList properties. Applicable if cell is edited
        updateObservableListProperties(cellName,cellId); //, firstNameCol, lastNameCol);
        olist = FXCollections.observableList(data.mapped);
        tableView.setItems(olist);
        //Enabling editing
        tableView.setEditable(true);
    }

    private void updateObservableListProperties(TableColumn cellName, TableColumn cellId) {
        cellName.setOnEditCommit(new EventHandler<CellEditEvent<MPoint, String>>() {
            @Override public void handle(CellEditEvent<MPoint, String> t) {
                ((MPoint) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setName(t.getNewValue());
            }
        });
        cellId.setOnEditCommit(new EventHandler<CellEditEvent<MPoint, String>>() {
            @Override public void handle(CellEditEvent<MPoint, String> t) {
                ((MPoint) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setId(t.getNewValue());
            }
        });
    }

    private void updateObservableListProperties(TableColumn emailCol, TableColumn firstNameCol,
                                                TableColumn lastNameCol) {
        //Modifying the email property in the ObservableList
        emailCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override public void handle(CellEditEvent<Person, String> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEmail(t.getNewValue());
            }
        });
        //Modifying the firstName property in the ObservableList
        firstNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override public void handle(CellEditEvent<Person, String> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setFirstName(t.getNewValue());
            }
        });
        //Modifying the lastName property in the ObservableList
        lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override public void handle(CellEditEvent<Person, String> t) {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setLastName(t.getNewValue());
            }
        });
    }

//.......................................................................................

    //Person object
    public static class Person {
        private BooleanProperty invited;
        private StringProperty firstName;
        private StringProperty lastName;
        private StringProperty email;

        private Person(boolean invited, String fName, String lName, String email) {
            this.invited = new SimpleBooleanProperty(invited);
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
            this.invited = new SimpleBooleanProperty(invited);

            this.invited.addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                    System.out.println(firstNameProperty().get() + " invited: " + t1);
                }
            });
        }

        public BooleanProperty invitedProperty() { return invited; }

        public StringProperty firstNameProperty() { return firstName; }

        public StringProperty lastNameProperty() { return lastName; }

        public StringProperty emailProperty() { return email; }

        public void setLastName(String lastName) { this.lastName.set(lastName); }

        public void setFirstName(String firstName) { this.firstName.set(firstName); }

        public void setEmail(String email) { this.email.set(email); }
    }

    //CheckBoxTableCell for creating a CheckBox in a table cell
    public static class CheckBoxTableCell<S, T> extends TableCell<S, T> {
        private final CheckBox checkBox;
        private ObservableValue<T> ov;

        public CheckBoxTableCell() {
            this.checkBox = new CheckBox();
            this.checkBox.setAlignment(Pos.CENTER);

            setAlignment(Pos.CENTER);
            setGraphic(checkBox);
        }

        @Override public void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(checkBox);
                if (ov instanceof BooleanProperty) {
                    checkBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
                }
                ov = getTableColumn().getCellObservableValue(getIndex());
                if (ov instanceof BooleanProperty) {
                    checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
                }
            }
        }
    }

    // EditingCell - for editing capability in a TableCell
    public static class EditingCell extends TableCell<MPoint, String> {
        private TextField textField;

        public EditingCell() {
        }

        @Override public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override public void cancelEdit() {
            super.cancelEdit();
            setText((String) getItem());
            setGraphic(null);
        }

        @Override public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

}
