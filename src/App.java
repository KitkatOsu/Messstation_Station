import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Darstellung als fxml-Datei
        Parent root = FXMLLoader.load(getClass().getResource("tabPage.fxml"));

        // Fenster erstellen und anzeigen
        Scene scene = new Scene(root);
        //scene.getRoot().setStyle("-fx-base:black");
        //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);

    }
}