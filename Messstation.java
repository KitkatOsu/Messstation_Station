
import java.util.ArrayList;

public class Messstation
{
    private SenseMap map;  
    
    private String name;
    private ArrayList<Messreihe> messreihen;

    public Messstation(String senseBoxId)
    {
        if (senseBoxId.equals("sim"))
        {
            map = new SenseMapSimulation();
        }
        else
        {
            map = new OpenSenseMap(senseBoxId);
        }

        // Daten Einlesen
        basisinfosAusSenseMapEinlesen();
        messreihenEinlesen();
       // aktuelleMesswerteEinlesen();   

    }

    public void aktuelleMesswerteEinlesen()
    {     
        for (Messreihe s: messreihen)
        {
            Messung m = map.getAktMessung(s.getSensorId());
            s.eineMessungHinzufuegen(m);
        }        
    }

    private void messreihenEinlesen()
    {
        for (Messreihe s:messreihen)
        {
            s.messungenHinzufuegen(map.getVieleMessungen(s.getSensorId()));
        }
    }

    private void basisinfosAusSenseMapEinlesen()
    {
        name = map.nameEinlesen();        
        messreihen = map.sensorenEinlesen();
    }
  
    public String getName()
    {
        return name;
    }
    
    public ArrayList<Messreihe> getMessreihen()
    {
        return messreihen;
    }

}

