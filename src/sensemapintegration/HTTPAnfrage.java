package sensemapintegration;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
 * Eine HTTP-Anfrage mit Hilfe der Klasse HttpClient
 * Geht ab Java 11
 */

public class HTTPAnfrage
{
  public static String get(String apiUrl, String pfad)
  {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl + pfad))
            .GET()
            .build();
    HttpResponse<String> response;
    try{
      response = client.send(request,HttpResponse.BodyHandlers.ofString());
      return response.body();
    }
    catch(Exception e)
    {
      System.out.println("Anfrage fehlgeschlagen");
      return null;
    }

  }
}