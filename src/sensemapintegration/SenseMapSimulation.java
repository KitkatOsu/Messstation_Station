package sensemapintegration;

import java.util.*;


/*
 * Klasse die die Anbindung an die sensemapintegration.OpenSenseMap simuliert
 */

public class SenseMapSimulation implements SenseMap
{
    String name = "BoxSimulation";
    
    // Werte der Box:
    double aktTemp = 13.0;
    double aktLuftdruck = 1013;
    double aktLuftfeuchte = 50;
    
    ArrayList<Messung> temperaturMesswerte;
    ArrayList<Messung> luftdruckMesswerte;
    ArrayList<Messung> luftfeuchteMesswerte;
    
    public SenseMapSimulation()
    {
        temperaturMesswerte = new ArrayList<Messung>();
        luftdruckMesswerte = new ArrayList<Messung>();
        luftfeuchteMesswerte = new ArrayList<Messung>();
        temperaturMesswerteErstellen();
        luftfeuchteMesswerteErstellen();
        luftdruckMesswerteErstellen();
    }
    
    
    public String nameEinlesen()
    {
        return name;
    }
    
    public ArrayList<Messreihe> sensorenEinlesen()
    {
        
        ArrayList<Messreihe> liste = new ArrayList<Messreihe>();

        Messreihe s1 = new Messreihe("1","Temperatur", "°C");
        Messreihe s2 = new Messreihe("2","Luftfeuchte" ,"%");
        Messreihe s3 = new Messreihe("3", "Luftdruck", "hPa");
        liste.add(s1);
        liste.add(s2);
        liste.add(s3);
        return liste;
    }
    
    public Messung getAktMessung(String sensorId)
    {
        double wert;
        if (sensorId.equals("1")) 
        {
            wert = aktTemp;
        }
        else if (sensorId.equals("2")) 
        {
            wert = aktLuftfeuchte;
        }
        else 
        {
            wert = aktLuftdruck;
        }
        String erzeugtAm = "2021-04-03T13:00:05.238Z";
        
        Messung messung = new Messung(wert,erzeugtAm);
        return messung;
    }
    
    public ArrayList<Messung> getVieleMessungen(String sensorId)
    {
        if (sensorId.equals("1")) 
        {
            return temperaturMesswerte;
        }
        else if (sensorId.equals("2")) 
        {
            return luftfeuchteMesswerte;
        }
        else 
        {
            return luftdruckMesswerte;
        }
    
    }
    
    private void temperaturMesswerteErstellen()
    {       
        temperaturMesswerte.add(new Messung(6,"2021-04-03T08:00:05.238Z"));       
        temperaturMesswerte.add(new Messung(8,"2021-04-03T09:00:05.238Z"));
        temperaturMesswerte.add(new Messung(11,"2021-04-03T10:00:05.238Z"));
        temperaturMesswerte.add(new Messung(12,"2021-04-03T11:00:05.238Z"));
        temperaturMesswerte.add(new Messung(12.5,"2021-04-03T12:00:05.238Z"));
        temperaturMesswerte.add(new Messung(14,"2021-04-03T13:00:05.238Z"));
        temperaturMesswerte.add(new Messung(7,"2021-04-03T14:00:05.238Z"));
    }
    
    private void luftdruckMesswerteErstellen()
    {       
        luftdruckMesswerte.add(new Messung(1000,"2021-04-03T08:00:05.238Z"));       
        luftdruckMesswerte.add(new Messung(1002,"2021-04-03T09:00:05.238Z"));
        luftdruckMesswerte.add(new Messung(1004,"2021-04-03T10:00:05.238Z"));
        luftdruckMesswerte.add(new Messung(1006,"2021-04-03T11:00:05.238Z"));
        luftdruckMesswerte.add(new Messung(1008,"2021-04-03T12:00:05.238Z"));
        luftdruckMesswerte.add(new Messung(1010,"2021-04-03T13:00:05.238Z"));
        luftdruckMesswerte.add(new Messung(1012,"2021-04-03T14:00:05.238Z"));
    }
    
    private void luftfeuchteMesswerteErstellen()
    {       
        luftfeuchteMesswerte.add(new Messung(20,"2021-04-03T08:00:05.238Z"));       
        luftfeuchteMesswerte.add(new Messung(22,"2021-04-03T09:00:05.238Z"));
        luftfeuchteMesswerte.add(new Messung(24,"2021-04-03T10:00:05.238Z"));
        luftfeuchteMesswerte.add(new Messung(26,"2021-04-03T11:00:05.238Z"));
        luftfeuchteMesswerte.add(new Messung(28,"2021-04-03T12:00:05.238Z"));
        luftfeuchteMesswerte.add(new Messung(30,"2021-04-03T13:00:05.238Z"));
        luftfeuchteMesswerte.add(new Messung(32,"2021-04-03T14:00:05.238Z"));
    }
}
