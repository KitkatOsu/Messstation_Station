import sensemapintegration.Messreihe;
import sensemapintegration.Messstation;

import java.util.*;

public class Konsolenausgabe
{
    Messstation station;
    
    public Konsolenausgabe(Messstation s)
    {
        station = s;
    }
    
    public void infosAusgeben()
    {
        System.out.println("********************************");
        System.out.println("Sensebox: " + station.getName());
        System.out.println("Sensoren: " );
        for (Messreihe r: station.getMessreihen())
        {
            System.out.println(" - " + r.toString());
        }
        System.out.println("********************************");
        System.out.println();
    }

    public void aktuelleWerteAusgeben()
    {
        System.out.println("----- Aktuelle Werte ------");
        for (Messreihe r : station.getMessreihen())
        {
            System.out.println(r.getTitel() + " \t" + r.getAktWert());
        }
        System.out.println("---------------------------");      
        System.out.println();
    }

    public void auswertungenAusgeben()
    {
        System.out.println("------- Auswertungen -------");
        for(Messreihe r: station.getMessreihen())
        {
            System.out.println(r.getTitel());                  
            System.out.println( "Max: " + r.getMaxWert());
            System.out.println( "Min: " + r.getMinWert());
            System.out.println();

        }
        System.out.println("---------------------------");       
        System.out.println();
    }

}
