import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MeasuringStation {
    private Sensor temp;
    private ArrayList<Float> tempValues;
    private ArrayList<Observer> observers;

    public MeasuringStation(){
        temp = new Sensor(this);
        tempValues = new ArrayList<Float>();
        observers = new ArrayList<Observer>();
    }

    public void runSensors() {
        MeasuringStation ms = this;
                TimerTask task = new TimerTask() {
                    Sensor t = temp;
                    MeasuringStation m = ms;
                    @Override
                    public void run() {
                        m.getTemp().measure();
                    }
                };

                Timer timer = new Timer();
                timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void registerObserver(Observer o){
        observers.add(o);
    }

    public void newValues() {
        tempValues.add(temp.getLatestValue());
        for(Observer o : observers){
            o.update();
        }
    }

    public Sensor getTemp(){
        return temp;
    }

    public ArrayList<Float> getTempValues(){
        return tempValues;
    }

}