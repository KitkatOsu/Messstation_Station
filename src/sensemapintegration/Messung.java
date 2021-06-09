package sensemapintegration;

public class Messung
{
    private double wert;
    private String erzeugtAm;
    
    public Messung(double wert_, String erzeugtAm_)
    {
        wert = wert_;
        erzeugtAm = erzeugtAm_;
    }
    
    public double getWert()
    {
        return wert;
    }
    
    public String getErzeugtAm()
    {
        return erzeugtAm;
    }
}
