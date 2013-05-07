package cpapp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.Prop;

public class Main extends Application {
    static public Scene scene;
    static public Stage stage;
    static public Main app ;
    @Override
    public void start(Stage primaryStage) throws Exception{
        AppCore.init();
        Parent root = FXMLLoader.load(getClass().getResource("view/fxml/mainform.fxml"));
        primaryStage.setTitle("CheckPoints 0.7.7");
        Double h = Prop.getDouble("stageH", 440.0);
        Double w = Prop.getDouble("stageW", 640.0);
        stage = primaryStage;
        app = this;
        scene = new Scene(root, w, h);
//        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<javafx.stage.WindowEvent>() {
            @Override
            public void handle(javafx.stage.WindowEvent windowEvent) {
                Prop.saveProp("stageH", scene.heightProperty());
                Prop.saveProp("stageW", scene.widthProperty());
                Prop.write();
                if(AppCore.ports.open)
                    AppCore.ports.closePort();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
