package sensemapintegration;

import java.util.ArrayList;

public class Messreihe
{
    private String sensorId;
    
    private String titel;
    private String einheit;
    
    private ArrayList<Messung> messungen;

    public Messreihe(String id_, String titel_, String einheit_)
    {
        sensorId = id_;
        titel = titel_;
        einheit = einheit_;
        messungen = new ArrayList<Messung>();
    }
    
    public String toString()
    {
        return titel + " ( " + einheit + " )"; 
    }
    
    public String getSensorId()
    {
        return sensorId;
    }
    
    public String getTitel()
    {
        return titel;
    }
    
    public double getAktWert()
    {
        return getAktMessung().getWert();
    }
    
    public void eineMessungHinzufuegen(Messung m)
    {
        messungen.add(0,m); // Neueste Messungen werden an der Stelle 0 eingefuegt
    }
    
    public void messungenHinzufuegen(ArrayList<Messung> neueMessungen)
    {
        messungen.addAll(neueMessungen);
    }
    
    public Messung getAktMessung()
    {
        int anzahl = messungen.size();
        if (anzahl == 0)
        {
            return new Messung(0,"kein Messwert vorhanden");
        }
        else
        {
            return messungen.get(0);
        }
    }
    
    public String getMaxWert()
    {
        return Auswertungen.max(messungen) + "";
    }

    public String getMinWert()
    {
        return Auswertungen.min(messungen) + "";
    }

    public String getEinheit() {
        return einheit;
    }
}
