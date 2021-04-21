import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
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
    private LineChart<?, ?> chart1;

    @FXML
    private Circle profile1;

    @FXML
    private Circle profile2;

    @FXML
    private Circle profile3;

    @FXML
    private Circle profile4;

    @FXML
    private TextField exp1;

    @FXML
    private TextField exp3;

    @FXML
    private TextField exp2;

    @FXML
    private TextField newData;

    @FXML
    private TextField profileText4;

    @FXML
    private TextField profileText3;

    @FXML
    private TextField profileText2;

    @FXML
    private TextField profileText1;

    @FXML
    private TextField impressum;

    @FXML
    void initialize() {
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'main.fxml'.";
        assert light1 != null : "fx:id=\"light1\" was not injected: check your FXML file 'main.fxml'.";
        assert light2 != null : "fx:id=\"light2\" was not injected: check your FXML file 'main.fxml'.";
        assert light3 != null : "fx:id=\"light3\" was not injected: check your FXML file 'main.fxml'.";
        assert searchbar != null : "fx:id=\"searchbar\" was not injected: check your FXML file 'main.fxml'.";
        assert chart1 != null : "fx:id=\"chart1\" was not injected: check your FXML file 'main.fxml'.";
        assert profile1 != null : "fx:id=\"profile1\" was not injected: check your FXML file 'main.fxml'.";
        assert profile2 != null : "fx:id=\"profile2\" was not injected: check your FXML file 'main.fxml'.";
        assert profile3 != null : "fx:id=\"profile3\" was not injected: check your FXML file 'main.fxml'.";
        assert profile4 != null : "fx:id=\"profile4\" was not injected: check your FXML file 'main.fxml'.";
        assert exp1 != null : "fx:id=\"exp1\" was not injected: check your FXML file 'main.fxml'.";
        assert exp3 != null : "fx:id=\"exp3\" was not injected: check your FXML file 'main.fxml'.";
        assert exp2 != null : "fx:id=\"exp2\" was not injected: check your FXML file 'main.fxml'.";
        assert newData != null : "fx:id=\"newData\" was not injected: check your FXML file 'main.fxml'.";
        assert profileText4 != null : "fx:id=\"profileText4\" was not injected: check your FXML file 'main.fxml'.";
        assert profileText3 != null : "fx:id=\"profileText3\" was not injected: check your FXML file 'main.fxml'.";
        assert profileText2 != null : "fx:id=\"profileText2\" was not injected: check your FXML file 'main.fxml'.";
        assert profileText1 != null : "fx:id=\"profileText1\" was not injected: check your FXML file 'main.fxml'.";
        assert impressum != null : "fx:id=\"impressum\" was not injected: check your FXML file 'main.fxml'.";

    }
}
