import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sensemapintegration.Auswertungen;
import sensemapintegration.Messreihe;
import sensemapintegration.Messstation;
import sensemapintegration.Observer;


import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Controller implements Observer {

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
    private TabPane tabPane;

    private String senseBoxId;

    private Messstation messstation;

    private ArrayList<LineChart<String, Number>> charts = new ArrayList<>();
    private ArrayList<Tab> tabs = new ArrayList<>();
    private ArrayList<ArrayList<TextField>> datas = new ArrayList<>(); //0 currentData, 1 min, 2 max, 3 average


    @FXML
    public void submitNewId(ActionEvent actionEvent) {
        newID.setText(newID.getText().toLowerCase());

        if (newID.getText().length() == 24 || newID.getText().equals("sim")) {
            senseBoxId = newID.getText();
            messstation.stopTimer();

            tabs.clear();
            charts.clear();
            tabPane.getTabs().remove(1, tabPane.getTabs().size());

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
        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'view.fxml'.";

        tabPane.setMaxSize(440, 532);

        senseBoxId = "607db857542eeb001cba21f0";
//        senseBoxId = "sim";

        for (int i = 0; i < 4; i++) {
            datas.add(new ArrayList<TextField>());
        }

        messstationInitialisieren();


    }

    public void changeLightColors() {
        // Formula: Y = (X-A)/(B-A) * (D-C) + C
        if (!temperatureData.getEinheit().equals("N/A")) {
            float newHue1 = (float) ((temperatureData.getAktWert() - temperatureData.getMinWert()) / (temperatureData.getMaxWert() - temperatureData.getMinWert()) * (0 - 270) + 270);
            Color newFill1 = Color.hsb(newHue1, 1, 1);
            light1.setFill(newFill1);
            light1.setEffect(new DropShadow(40, newFill1));
        }

        if (!pressureData.getEinheit().equals("N/A")) {
            float newHue2 = (float) ((pressureData.getAktWert() - pressureData.getMinWert()) / (pressureData.getMaxWert() - pressureData.getMinWert()) * (0 - 270) + 270);
            Color newFill2 = Color.hsb(newHue2, 1, 1);
            light2.setFill(newFill2);
            light2.setEffect(new DropShadow(40, newFill2));
        }
        if (!humidityData.getEinheit().equals("N/A")) {
            float newHue3 = (float) ((humidityData.getAktWert() - humidityData.getMinWert()) / (humidityData.getMaxWert() - humidityData.getMinWert()) * (0 - 270) + 270);
            Color newFill3 = Color.hsb(newHue3, 1, 1);
            light3.setFill(newFill3);
            light3.setEffect(new DropShadow(40, newFill3));
        }
    }

    private void messstationInitialisieren() {
        messstation = new Messstation(senseBoxId);
        messstation.addObserver(this);
        messstation.startTimer();

        ArrayList<Messreihe> messreihen = messstation.getMessreihen();

        for (Messreihe m : messreihen) {
            Tab newTab = new Tab(m.getTitel());
            tabs.add(newTab);
            VBox vb = new VBox();
            vb.setSpacing(30);



            LineChart<String, Number> chart = new LineChart(new CategoryAxis(), new NumberAxis());
            charts.add(chart);
            chart.setMaxSize(430, 300);


            Label currentDataLabel = new Label("Current Value:");
            currentDataLabel.setTextFill(Color.WHITE);
            TextField currentDataTextField = new TextField();
            currentDataTextField.setEditable(false);
            datas.get(0).add(currentDataTextField);


            Label minDataLabel = new Label("Minimum Value:");
            minDataLabel.setTextFill(Color.WHITE);
            TextField minDataTextField = new TextField();
            minDataTextField.setEditable(false);
            datas.get(1).add(minDataTextField);

            Label maxDataLabel = new Label("Maximimum Value:");
            maxDataLabel.setTextFill(Color.WHITE);
            TextField maxDataTextField = new TextField();
            maxDataTextField.setEditable(false);
            datas.get(2).add(maxDataTextField);

            Label averageDataLabel = new Label("Average Value:");
            averageDataLabel.setTextFill(Color.WHITE);
            TextField averageDataTextField = new TextField();
            averageDataTextField.setEditable(false);
            datas.get(3).add(averageDataTextField);


            HBox currentData = new HBox();
            currentData.getChildren().addAll(currentDataLabel, currentDataTextField);
            currentData.setSpacing(20);
            currentData.setAlignment(Pos.CENTER);

            HBox minData = new HBox();
            minData.getChildren().addAll(minDataLabel, minDataTextField);
            minData.setSpacing(20);
            minData.setAlignment(Pos.CENTER);


            HBox maxData = new HBox();
            maxData.getChildren().addAll(maxDataLabel, maxDataTextField);
            maxData.setSpacing(20);
            maxData.setAlignment(Pos.CENTER);

            HBox averageData = new HBox();
            averageData.getChildren().addAll(averageDataLabel, averageDataTextField);
            averageData.setSpacing(20);
            averageData.setAlignment(Pos.CENTER);

            vb.getChildren().add(chart);
            vb.getChildren().addAll(currentData, minData, maxData, averageData);



            newTab.setContent(vb);
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

        updateDiagrams();
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

    public void updateDiagrams() {

        for (int i = 0; i < messstation.getMessreihen().size(); i++) {

            Messreihe r = messstation.getMessreihen().get(i);
            XYChart.Series series = new XYChart.Series();
            for (int j = 0; j < 11; j++) {
                Date currentDate = new Date();
                Date dateToGetDataFrom = new Date(currentDate.getTime() - Duration.ofHours(4 * j).toMillis());
                double averageValuesOfDate = Auswertungen.average(r.getMessungenAm(dateToGetDataFrom));

                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'HH");
                String str = ft.format(dateToGetDataFrom);

                if (averageValuesOfDate != -9999)
                    series.getData().add(new XYChart.Data(str, averageValuesOfDate));
            }
            series.setName(r.getTitel());
            charts.get(i).getData().addAll(series);
        }
    }

    public void changeTheme(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://blog.weekdone.com/why-you-should-switch-on-dark-mode/").toURI());
        } catch (Exception e) {
        }
    }


}
