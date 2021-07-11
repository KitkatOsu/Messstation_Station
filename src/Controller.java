import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sensemapintegration.Messreihe;
import sensemapintegration.Messstation;
import sensemapintegration.Observer;

public class Controller implements Observer {

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
    private Messreihe temperatureData;

    @FXML
    private TextField humidity;
    private Messreihe humidityData;

    @FXML
    private TextField pressure;
    private Messreihe pressureData;

    @FXML
    private TextField newID;

    @FXML
    private LineChart<Number, Number> tempDiagram;

    @FXML
    private TabPane tabPane;

    private String senseBoxId;

    private Messstation messstation;

    @FXML
    public void submitNewId(ActionEvent actionEvent) {
        newID.setText(newID.getText().toLowerCase());

        if (newID.getText().length() == 24  || newID.getText().equals("sim")) {
            senseBoxId = newID.getText();
            messstation.stopTimer();
            messstationInitialisieren();
            temperature.clear();
            pressure.clear();
            humidity.clear();
        }
        newID.clear();
    }

    @FXML
    void initialize() {
        assert light1 != null : "fx:id=\"light1\" was not injected: check your FXML file 'view.fxml'.";
        assert light2 != null : "fx:id=\"light2\" was not injected: check your FXML file 'view.fxml'.";
        assert light3 != null : "fx:id=\"light3\" was not injected: check your FXML file 'view.fxml'.";
        assert searchbar != null : "fx:id=\"searchbar\" was not injected: check your FXML file 'view.fxml'.";
        assert temperature != null : "fx:id=\"temperature\" was not injected: check your FXML file 'view.fxml'.";
        assert humidity != null : "fx:id=\"humidity\" was not injected: check your FXML file 'view.fxml'.";
        assert pressure != null : "fx:id=\"pressure\" was not injected: check your FXML file 'view.fxml'.";
        assert newID != null : "fx:id=\"newID\" was not injected: check your FXML file 'view.fxml'.";
        assert tempDiagram != null : "fx:id=\"tempDiagram\" was not injected: check your FXML file 'view.fxml'.";
        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'view.fxml'.";

        senseBoxId = "607db857542eeb001cba21f0";
//        senseBoxId = "sim";

        messstationInitialisieren();
        updateDiagrams();
    }

    public void changeLightColors() {
        // Formula: Y = (X-A)/(B-A) * (D-C) + C
        float newHue1 = (float) ((temperatureData.getAktWert() + 10) / (55 + 10) * (0 - 270) + 270);
        Color newFill1 = Color.hsb(newHue1, 1, 1);
        light1.setFill(newFill1);
        light1.setEffect(new DropShadow(40, newFill1));

        float newHue2 = (float) ((pressureData.getAktWert()+900)/(1400+900) * (0-270) + 270);
        Color newFill2 = Color.hsb(newHue2,1,1);
        light2.setFill(newFill2);
        light2.setEffect(new DropShadow(40, newFill2));

        float newHue3 = (float) ((humidityData.getAktWert() + 0)/(100+0) * (0-270) + 270);
        Color newFill3 = Color.hsb(newHue3,1,1);
        light3.setFill(newFill3);
        light3.setEffect(new DropShadow(40, newFill3));
    }

    private void messstationInitialisieren() {
        messstation = new Messstation(senseBoxId);
        messstation.addObserver(this);
        messstation.startTimer();

        ArrayList<Messreihe> messreihen = messstation.getMessreihen();

        for (Messreihe m : messreihen){
            Tab newTab = new Tab();
            newTab.setText(m.getTitel());

            LineChart<Number, Number> chart = new LineChart(new NumberAxis(), new NumberAxis());

            newTab.setContent(chart);
            tabPane.getTabs().add(newTab);
        }

        temperatureData = messstation.getMessreiheMitEinheit("°C");
        if (temperatureData == null)
            temperatureData = new Messreihe("N/A", "Temperatur", "N/A");

        pressureData = messstation.getMessreiheMitEinheit("hPa");
        if (pressureData == null)
            pressureData = new Messreihe("N/A", "Luftdruck", "N/A");

        humidityData = messstation.getMessreiheMitEinheit("%");
        if (humidityData == null)
            humidityData = new Messreihe("N/A", "rel. Luftfeuchte", "N/A");
    }

    @Override
    public void update() {
        updateTextfields();
        changeLightColors();
    }

    private void updateTextfields() {

        if (!temperatureData.getEinheit().equalsIgnoreCase("N/A"))
            temperature.setText(temperatureData.getAktWert() + temperatureData.getEinheit());
        else
            temperature.setText(temperatureData.getEinheit());

        if (!pressureData.getEinheit().equalsIgnoreCase("N/A"))
            pressure.setText(pressureData.getAktWert() + pressureData.getEinheit());
        else
            pressure.setText(pressureData.getEinheit());

        if (!humidityData.getEinheit().equalsIgnoreCase("N/A"))
            humidity.setText(humidityData.getAktWert() + humidityData.getEinheit());
        else
            humidity.setText(humidityData.getEinheit());

    }

    public void updateDiagrams(){
        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data(1, 1));
        series.getData().add(new XYChart.Data(2, 2));
        series.getData().add(new XYChart.Data(3, 3));
        series.getData().add(new XYChart.Data(4, 4));
        series.getData().add(new XYChart.Data(5, 5));
        series.getData().add(new XYChart.Data(6, 6));
        series.getData().add(new XYChart.Data(7, 7));
        series.getData().add(new XYChart.Data(8, 8));
        series.getData().add(new XYChart.Data(9, 9));
        series.getData().add(new XYChart.Data(10, 10));
        series.getData().add(new XYChart.Data(11, 11));
        series.getData().add(new XYChart.Data(12, 12));

        tempDiagram.getData().addAll(series);
    }
}
