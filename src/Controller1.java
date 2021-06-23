import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import java.util.Timer;
import java.util.TimerTask;
import sensemapintegration.Messstation;

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
    private TextField humidity;

    @FXML
    private TextField pressure;

    @FXML
    void initialize(){
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'main.fxml'.";
        assert light1 != null : "fx:id=\"light1\" was not injected: check your FXML file 'main.fxml'.";
        assert light2 != null : "fx:id=\"light2\" was not injected: check your FXML file 'main.fxml'.";
        assert light3 != null : "fx:id=\"light3\" was not injected: check your FXML file 'main.fxml'.";
        assert searchbar != null : "fx:id=\"searchbar\" was not injected: check your FXML file 'main.fxml'.";
        assert temperature != null : "fx:id=\"temperature\" was not injected: check your FXML file 'main.fxml'.";
        assert humidity != null : "fx:id=\"humidity\" was not injected: check your FXML file 'main.fxml'.";
        assert pressure != null : "fx:id=\"co2\" was not injected: check your FXML file 'main.fxml'.";

        printNewData();
    }

    void printNewData(){
        Controller1 cont = this;
        TimerTask task = new TimerTask()
        {
            Controller1 c = cont;

            Messstation mess =  new Messstation("607db857542eeb001cba21f0");

            @Override
            public void run ()
            {
                mess.messreihenEinlesen();
            String temperatureData = mess.getMessreihe("Temperatur").getAktWert();
            String humidityData = mess.getMessreihe("rel. Luftfeuchte").getAktWert();
            String pressureData =  mess.getMessreihe("Luftdruck").getAktWert();
            c.temperature.setText(temperatureData + "°C"); // better if the getEinheit() method of messreihe is used
            c.humidity.setText(humidityData + "%");
            c.pressure.setText(pressureData + "hPa");

            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 1, 5000);
    }
}

