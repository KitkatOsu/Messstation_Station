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
import java.text.ParseException;
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
    private ArrayList<TextField[]> textFieldsForTabs = new ArrayList<>(); //0 current, 1 min, 2 max, 3 average






    //Submit a new ID through the Submission box + button
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
        assert temperature != null : "fx:id=\"temperature\" was not injected: check your FXML file 'view.fxml'.";
        assert humidity != null : "fx:id=\"humidity\" was not injected: check your FXML file 'view.fxml'.";
        assert pressure != null : "fx:id=\"pressure\" was not injected: check your FXML file 'view.fxml'.";
        assert newID != null : "fx:id=\"newID\" was not injected: check your FXML file 'view.fxml'.";
        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'view.fxml'.";

        tabPane.setMaxSize(440, 532);

        senseBoxId = "607db857542eeb001cba21f0";

        messstationInitialisieren();


    }



    //Change the light colors of the circles according to the current value and its max and min value using a formula that is big brain
    public void changeLightColors() {
        // Mapping formula: Y = (X-A)/(B-A) * (D-C) + C
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




    //Initialize the messstation, set attributes for the tabs, add other stuff to the dynamic tabs etc.
    private void messstationInitialisieren() {
        messstation = new Messstation(senseBoxId);
        for (int i = 0; i < messstation.getMessreihen().size(); i++)
            textFieldsForTabs.add(new TextField[4]);

        messstation.addObserver(this);

        dynamicallyCreateTabsForEveryMessreihe(messstation.getMessreihen());

        //set these 3 TextFields to Not Available if their corresponding Messreihen don't exist
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

        messstation.startTimer();
    }

    //Calling the methods to update
    @Override
    public void update() {
        updateTextfields();
        changeLightColors();
    }




    //Update all the TextFields at once
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

        //Update the current, min, max and average values in
        //the TextFields for the dynamically created tabs
        for (int i = 0; i < messstation.getMessreihen().size(); i++) {
            Messreihe r = messstation.getMessreihen().get(i);
            String einheit = r.getEinheit();

            textFieldsForTabs.get(i)[0].setText(r.getAktWert() + einheit);
            textFieldsForTabs.get(i)[1].setText(r.getMinWert() + einheit);
            textFieldsForTabs.get(i)[2].setText(r.getMaxWert() + einheit);
            textFieldsForTabs.get(i)[3].setText(r.getAverageWert() + einheit);

        }


    }





    //Updating all the Diagrams
    public void updateDiagrams() {

        for (int i = 0; i < messstation.getMessreihen().size(); i++) {

            Messreihe r = messstation.getMessreihen().get(i);
            XYChart.Series series = new XYChart.Series();
            for (int j = 13; j > 0; j--) {
                //Getting the Messungen from 4*j hours before the date of the last Messung
                if (r.getMessungen().isEmpty())
                    break;

                String dateOfLastMessungString = r.getAktMessung().getErzeugtAm().substring(0,13);
                Date dateOfLastMessung = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH");


                try {
                    dateOfLastMessung = dateFormat.parse(dateOfLastMessungString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                int hourInterval = 4;
                Date dateToGetDataFrom = new Date(dateOfLastMessung.getTime() - Duration.ofHours(hourInterval * j - hourInterval).toMillis());
                double averageValuesOfDate = Auswertungen.average(r.getMessungenAm(dateToGetDataFrom, dateFormat));

                //Date that gets displayed on the x-Axis
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd-HH'h'");
                String str = ft.format(dateToGetDataFrom);

                //-9999 is the value that gets returned when there is an error
                if (averageValuesOfDate != -9999)
                    series.getData().add(new XYChart.Data(str, averageValuesOfDate));
            }

            //Setting name of chart and giving it all the Data
            series.setName(r.getTitel());
            charts.get(i).getData().addAll(series);
        }
    }




    // Changes the Theme of the application.... Yeah for sure ;)
    public void changeTheme(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://blog.weekdone.com/why-you-should-switch-on-dark-mode/").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //Making new Tabs depending on what Messreihen we get from the Messstation
    //We're basically coding an FXML in java here
    //this allows us to add more tabs when certain conditions are met, e.g. a
    //new ID was submitted then the old tabs get deleted and this method is called
    //to create the new tabs
    public void dynamicallyCreateTabsForEveryMessreihe(ArrayList<Messreihe> messreihen){

        for (int i = 0; i < messreihen.size(); i++) {
            //Making a new Tab and a VBox which will be filled
            //with several HBoxes later on
            Tab newTab = new Tab(messreihen.get(i).getTitel());
            tabs.add(newTab);
            VBox vb = new VBox();
            vb.setSpacing(20);


            //Making a new chart for that Tab
            LineChart<String, Number> chart = new LineChart(new CategoryAxis(), new NumberAxis());
            charts.add(chart);
            chart.setMaxSize(430, 300);


            //Making new Labels and Textfields for every dynamically created tab
            //Adding them to the textFieldsForTabs ArrayList to have a reference to them later on
            Label currentDataLabel = new Label("Current Value:");
            currentDataLabel.setTextFill(Color.WHITE);
            TextField currentDataTextField = new TextField();
            currentDataTextField.setEditable(false);
            textFieldsForTabs.get(i)[0] = currentDataTextField;


            Label minDataLabel = new Label("Minimum Value:");
            minDataLabel.setTextFill(Color.WHITE);
            TextField minDataTextField = new TextField();
            minDataTextField.setEditable(false);
            textFieldsForTabs.get(i)[1] = minDataTextField;


            Label maxDataLabel = new Label("Maximimum Value:");
            maxDataLabel.setTextFill(Color.WHITE);
            TextField maxDataTextField = new TextField();
            maxDataTextField.setEditable(false);
            textFieldsForTabs.get(i)[2] = maxDataTextField;


            Label averageDataLabel = new Label("Average Value:");
            averageDataLabel.setTextFill(Color.WHITE);
            TextField averageDataTextField = new TextField();
            averageDataTextField.setEditable(false);
            textFieldsForTabs.get(i)[3] = averageDataTextField;



            //creating two VBoxes that are being filled with the
            //Labels/TextFields and added in a HBox
            VBox labelBox = new VBox();
            labelBox.getChildren().addAll(currentDataLabel, minDataLabel, maxDataLabel, averageDataLabel);
            labelBox.setSpacing(18);
            labelBox.setTranslateY(5);
            labelBox.setAlignment(Pos.BASELINE_RIGHT);


            VBox textFieldBox = new VBox();
            textFieldBox.getChildren().addAll(currentDataTextField, minDataTextField, maxDataTextField, averageDataTextField);
            textFieldBox.setSpacing(10);
            textFieldBox.setAlignment(Pos.BASELINE_RIGHT);


            HBox labelsAndTextFields = new HBox();
            labelsAndTextFields.getChildren().addAll(labelBox, textFieldBox);
            labelsAndTextFields.setAlignment(Pos.CENTER);
            labelsAndTextFields.setSpacing(20);

            //Adding the chart and the HBox to the VBox
            vb.getChildren().addAll(chart, labelsAndTextFields);

            //Adding the VBox to the new Tab which is then added
            //to the TabPane
            newTab.setContent(vb);
            tabPane.getTabs().add(newTab);

        }
    }

}
