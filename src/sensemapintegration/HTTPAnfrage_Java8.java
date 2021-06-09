package sensemapintegration;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPAnfrage_Java8
{
 
  /*
   * Eine HTTP-Anfrage mit HttpURLConnection
   */
  public static String get(String apiUrl, String pfad) {  
    String erg = null;
    try {
      URL url = new URL(apiUrl + pfad);                      
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      String readStream = readStream(con.getInputStream());
      // Give output for the command line
      //System.out.println(readStream);
      erg = readStream;
    } 
    catch (Exception e) {
      System.out.println("Fehler! Verbindung zu " + apiUrl + pfad + " war nicht möglich!");
      e.printStackTrace();
    }
    return erg;
  }

  private  static String readStream(InputStream in) {
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    try{
      String nextLine = "";
      while ((nextLine = reader.readLine()) != null) {
        sb.append(nextLine);
      }
      reader.close();
    } 
    catch (IOException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }
}
