package sensemapintegration;

import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Messstation {
    private SenseMap map;

    private String name;
    private ArrayList<Messreihe> messreihen;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public static final String TEMPERATURE = "Temperatur";
    public static final String HUMIDITY = "rel. Luftfeuchte";
    public static final String PRESSURE = "Luftdruck";

    public Messstation(String senseBoxId) {
        if (senseBoxId.equals("sim")) {
            map = new SenseMapSimulation();
        } else {
            map = new OpenSenseMap(senseBoxId);
        }

        // Daten Einlesen
        basisinfosAusSenseMapEinlesen();
        messreihenEinlesen();
        // aktuelleMesswerteEinlesen();

    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void startTimer() {
        Messstation a = this;
        TimerTask task = new TimerTask() {

            Messstation m = a;

            @Override
            public void run() {
                m.messreihenEinlesen();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 1, 10000);
    }

    public void aktuelleMesswerteEinlesen() {
        for (Messreihe s : messreihen) {
            Messung m = map.getAktMessung(s.getSensorId());
            s.eineMessungHinzufuegen(m);
        }
    }

    public void messreihenEinlesen() {
        for (Messreihe s : messreihen) {
            s.messungenHinzufuegen(map.getVieleMessungen(s.getSensorId()));
        }

        for(Observer o : observers){
            o.update();
        }
    }

    private void basisinfosAusSenseMapEinlesen() {
        name = map.nameEinlesen();
        messreihen = map.sensorenEinlesen();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Messreihe> getMessreihen() {
        return messreihen;
    }

    public Messreihe getMessreihe(String nameOfMessreihe) {
        for (Messreihe r : messreihen) {
            if (nameOfMessreihe.equals(r.getTitel()))
                return r;
        }
        return null;
    }

}
