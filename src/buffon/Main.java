package buffon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Graham Earley, Carleton College, CS257
 *
 * This is the Main class for the program. It loads the FXML
 * and sets up the stage, and then uses JavaFX methods to launch the
 * application.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("buffon.fxml"));
        primaryStage.setTitle("Buffon's Needle Simulator");
        primaryStage.setScene(new Scene(root, 550, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
