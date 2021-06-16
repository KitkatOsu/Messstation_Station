import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Controller1 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane pane;

    @FXML
    private Circle light1;

    @FXML
    private Circle light2;

    @FXML
    private Circle light3;

    @FXML
    private TextField searchbar;

    @FXML
    private TextField temperature;

    @FXML
    private TextField impressum;

    @FXML
    private TextField humidity;

    @FXML
    private TextField co2;

    @FXML
    void initialize() {
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'main.fxml'.";
        assert light1 != null : "fx:id=\"light1\" was not injected: check your FXML file 'main.fxml'.";
        assert light2 != null : "fx:id=\"light2\" was not injected: check your FXML file 'main.fxml'.";
        assert light3 != null : "fx:id=\"light3\" was not injected: check your FXML file 'main.fxml'.";
        assert searchbar != null : "fx:id=\"searchbar\" was not injected: check your FXML file 'main.fxml'.";
        assert temperature != null : "fx:id=\"temperature\" was not injected: check your FXML file 'main.fxml'.";
        assert impressum != null : "fx:id=\"impressum\" was not injected: check your FXML file 'main.fxml'.";
        assert humidity != null : "fx:id=\"humidity\" was not injected: check your FXML file 'main.fxml'.";
        assert co2 != null : "fx:id=\"co2\" was not injected: check your FXML file 'main.fxml'.";

    }
}

