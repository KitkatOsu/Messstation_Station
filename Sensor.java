import java.util.Random;
import java.util.Stack;

public class Sensor {
    private Stack<Float> values;
    private MeasuringStation measuringStation;

    public Sensor(MeasuringStation measuringStation){
        values = new Stack<Float>();
        this.measuringStation = measuringStation;
    }

    public void measure(){
        Random random = new Random();
        values.add((float) (random.nextFloat()*40-9));
        measuringStation.newValues();
    }

    public Stack<Float> getValues(){
        return values;
    }


}