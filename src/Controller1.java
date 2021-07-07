import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import java.util.Timer;
import java.util.TimerTask;

import sensemapintegration.Messreihe;
import sensemapintegration.Messstation;
import sensemapintegration.Observer;

public class Controller1 implements Observer {

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
    private TextField newID;

    @FXML
    private TextField temperature;
    private Messreihe temperatureData;

    @FXML
    private TextField humidity;
    private Messreihe humidityData;

    @FXML
    private TextField pressure;
    private Messreihe pressureData;


    private String senseBoxId = "607db857542eeb001cba21f0";
    private Messstation messstation;

    @FXML
    void initialize(){
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'main.fxml'.";
        assert light1 != null : "fx:id=\"light1\" was not injected: check your FXML file 'main.fxml'.";
        assert light2 != null : "fx:id=\"light2\" was not injected: check your FXML file 'main.fxml'.";
        assert light3 != null : "fx:id=\"light3\" was not injected: check your FXML file 'main.fxml'.";
        assert searchbar != null : "fx:id=\"searchbar\" was not injected: check your FXML file 'main.fxml'.";
        assert newID != null : "fx:id=\"newID\" was not injected: check your FXML file 'main.fxml'.";
        assert temperature != null : "fx:id=\"temperature\" was not injected: check your FXML file 'main.fxml'.";
        assert humidity != null : "fx:id=\"humidity\" was not injected: check your FXML file 'main.fxml'.";
        assert pressure != null : "fx:id=\"co2\" was not injected: check your FXML file 'main.fxml'.";

        messstation = new Messstation(senseBoxId);
        messstation.messreihenEinlesen();
        messstation.addObserver(this);
        messstation.startTimer();
    }


    public void changeLightColors(){

    }

    @FXML
    public void submitNewId(javafx.event.ActionEvent actionEvent) {
        if(newID.getText().length()==24)
            senseBoxId = newID.getText();
        newID.clear();
    }

    @Override
    public void update() {
        temperatureData = messstation.getMessreihe(Messstation.TEMPERATURE);
        humidityData = messstation.getMessreihe(Messstation.HUMIDITY);
        pressureData = messstation.getMessreihe(Messstation.PRESSURE);


        updateTextfields();
    }

    private void updateTextfields() {
        temperature.setText(temperatureData.getAktWert() + temperatureData.getEinheit());
        humidity.setText(humidityData.getAktWert() + humidityData.getEinheit());
        pressure.setText(pressureData.getAktWert() + pressureData.getEinheit());
    }
}