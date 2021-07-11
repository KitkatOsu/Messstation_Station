import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TextField humidity;

    @FXML
    private TextField pressure;

    @FXML
    private TextField newID;

    @FXML
    void initialize() {
        assert light1 != null : "fx:id=\"light1\" was not injected: check your FXML file 'tab.fxml'.";
        assert light2 != null : "fx:id=\"light2\" was not injected: check your FXML file 'tab.fxml'.";
        assert light3 != null : "fx:id=\"light3\" was not injected: check your FXML file 'tab.fxml'.";
        assert searchbar != null : "fx:id=\"searchbar\" was not injected: check your FXML file 'tab.fxml'.";
        assert temperature != null : "fx:id=\"temperature\" was not injected: check your FXML file 'tab.fxml'.";
        assert humidity != null : "fx:id=\"humidity\" was not injected: check your FXML file 'tab.fxml'.";
        assert pressure != null : "fx:id=\"pressure\" was not injected: check your FXML file 'tab.fxml'.";
        assert newID != null : "fx:id=\"newID\" was not injected: check your FXML file 'tab.fxml'.";

    }
}