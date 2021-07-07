import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Controller2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private LineChart<?, ?> tempDiagram;

    @FXML
    void initialize() {
        assert tempDiagram != null : "fx:id=\"tempDiagram\" was not injected: check your FXML file 'diagrams.fxml'.";
        updateDiagram();
    }

    public void updateDiagram(){
        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data("1", 1));
        series.getData().add(new XYChart.Data("2", 2));
        series.getData().add(new XYChart.Data("3", 3));
        series.getData().add(new XYChart.Data("4", 4));
        series.getData().add(new XYChart.Data("5", 5));
        series.getData().add(new XYChart.Data("6", 6));
        series.getData().add(new XYChart.Data("7", 7));
        series.getData().add(new XYChart.Data("8", 8));
        series.getData().add(new XYChart.Data("9", 9));
        series.getData().add(new XYChart.Data("10", 10));
        series.getData().add(new XYChart.Data("11", 11));
        series.getData().add(new XYChart.Data("12", 12));

        tempDiagram.getData().addAll(series);
    }
}