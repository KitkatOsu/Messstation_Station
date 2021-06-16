import java.util.Random;
import java.util.Stack;

public class Sensor {
    private float latestValue;
    private MeasuringStation measuringStation;

    public Sensor(MeasuringStation measuringStation){
        latestValue = 0;
        this.measuringStation = measuringStation;
    }

    public void measure(){
        Random random = new Random();
        latestValue = random.nextFloat()*40-9;
        measuringStation.newValues();
    }

    public float getLatestValue(){
        return latestValue;
    }


}