import java.util.*;

public interface SenseMap
{
    public String nameEinlesen();
    public ArrayList<Messreihe> sensorenEinlesen();
    public Messung getAktMessung(String sensorId);
    public ArrayList<Messung> getVieleMessungen(String sensorId);
    
}
