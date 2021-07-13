package sensemapintegration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Messstation {
    private SenseMap map;

    private String name;
    private ArrayList<Messreihe> messreihen;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private TimerTask updater;


    public Messstation(String senseBoxId) {
        if (senseBoxId.equals("sim")) {
            map = new SenseMapSimulation();
        } else {
            map = new OpenSenseMap(senseBoxId);
        }

        // Read Data
        basisinfosAusSenseMapEinlesen();
        messreihenEinlesen();
        // aktuelleMesswerteEinlesen();

    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    //Method to stop the timer
    public void stopTimer() {
        updater.cancel();
    }

    //Start a timer for all types of things to work and count
    public void startTimer() {
        Messstation a = this;
        updater = new TimerTask() {

            Messstation m = a;

            @Override
            public void run() {
                m.aktuelleMesswerteEinlesen();
                m.updateAll();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(updater, 1, 10000);


    }

    private void updateAll() {
        for (Observer o : observers) {
            o.update();
        }
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
        updateAll();
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


    //two getters that return a Messreihe according to the name/unit given to them
    public Messreihe getMessreihe(String nameOfMessreihe) {
        for (Messreihe r : messreihen) {
            if (nameOfMessreihe.equals(r.getTitel()))
                return r;
        }
        System.out.println("ERROR: Messreihe with name " + nameOfMessreihe + " doesn't exist");
        return null;
    }

    public Messreihe getMessreiheMitEinheit(String einheit) {
        for (Messreihe r : messreihen) {
            if (einheit.equals(r.getEinheit()))
                return r;
        }
        System.out.println("ERROR: Messreihe with unit " + einheit + " doesn't exist");
        return null;
    }
}
