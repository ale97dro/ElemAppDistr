package http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ApplicazioniDistribuite
{
    /**
     * Invia richiesta http e guardiamo il contenuto della risposta
     */
    public static void esercizio1() throws IOException
    {
        URL url = new URL("http://www.supsi.ch/home.html");
        HttpURLConnection con = (HttpURLConnection) url.openConnection(); //creo la connessione
        con.setRequestMethod("GET"); //setto il metodo con cui viene effettuata la richiesta
        con.setRequestProperty("Content-Type", "application/json");

        Map<String, List<String>> requestHeaders = con.getRequestProperties(); //prendo gli headers della richiesta (devo farlo qui e non sotto)
        Map<String, List<String>> responseHeaders = con.getHeaderFields(); //prendo gli headers della risposta


        System.out.println("Request headers");
        for(String s : requestHeaders.keySet())
            System.out.println(s + ": " + requestHeaders.get(s));

        System.out.println("Response headers");
        for(String s : responseHeaders.keySet())
            System.out.println(s + ": " + responseHeaders.get(s));


        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while((inputLine = in.readLine()) != null)
            content.append(inputLine);
        in.close();


        System.out.println("Contenuto della pagina");
        System.out.println(content.toString());

        Document doc = Jsoup.parse(content.toString());
        Elements media = doc.select("[src]");

        System.out.println("Number of resources: " + media.size());

        byte[] b = content.toString().getBytes("UTF-8");
        System.out.println("Page bytes: " + b.length);
    }
}
