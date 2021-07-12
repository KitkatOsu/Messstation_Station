package sensemapintegration;

import java.util.*;

public class Auswertungen
{
   
   
   public static double max(ArrayList<Messung> messungen)
   {
       double max = messungen.get(0).getWert();
       for (Messung m : messungen)
       {
           if (m.getWert() > max)
           {
               max = m.getWert();
           }
        }
        return max;
    }
    
    public static double min(ArrayList<Messung> messungen)
   {
       double min = messungen.get(0).getWert();
       for (Messung m : messungen)
       {
           if (m.getWert() <  min)
           {
               min = m.getWert();
           }
        }
        return min;
    }


    public static double average(ArrayList<Messung> messungen) {
       double sumOfMessungenValues = 0;

       for (Messung m : messungen){
           sumOfMessungenValues += m.getWert();
       }
       double averageOfValues = sumOfMessungenValues / messungen.size();
       return averageOfValues;
    }
}
