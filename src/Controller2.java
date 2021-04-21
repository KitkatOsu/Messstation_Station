import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private LineChart<?, ?> lineChart1;

    @FXML
    private BarChart<?, ?> barChart1;

    @FXML
    private LineChart<?, ?> lineChart2;

    @FXML
    private TextField chartText1;

    @FXML
    private TextField chartText2;

    @FXML
    private TextField barChartText1;

    @FXML
    private LineChart<?, ?> chartText3;

    @FXML
    private Button Tab1;

    @FXML
    private Button Tab2;

    @FXML
    private Button Tab3;

    @FXML
    void initialize() {
        assert lineChart1 != null : "fx:id=\"lineChart1\" was not injected: check your FXML file 'diagrams.fxml'.";
        assert barChart1 != null : "fx:id=\"barChart1\" was not injected: check your FXML file 'diagrams.fxml'.";
        assert lineChart2 != null : "fx:id=\"lineChart2\" was not injected: check your FXML file 'diagrams.fxml'.";
        assert chartText1 != null : "fx:id=\"chartText1\" was not injected: check your FXML file 'diagrams.fxml'.";
        assert chartText2 != null : "fx:id=\"chartText2\" was not injected: check your FXML file 'diagrams.fxml'.";
        assert barChartText1 != null : "fx:id=\"barChartText1\" was not injected: check your FXML file 'diagrams.fxml'.";
        assert chartText3 != null : "fx:id=\"chartText3\" was not injected: check your FXML file 'diagrams.fxml'.";
        assert Tab1 != null : "fx:id=\"Tab1\" was not injected: check your FXML file 'diagrams.fxml'.";
        assert Tab2 != null : "fx:id=\"Tab2\" was not injected: check your FXML file 'diagrams.fxml'.";
        assert Tab3 != null : "fx:id=\"Tab3\" was not injected: check your FXML file 'diagrams.fxml'.";

    }
}
