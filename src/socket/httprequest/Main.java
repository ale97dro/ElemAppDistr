package socket.httprequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Socket client = new Socket("www.google.it", 80);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));


            writer.println("GET / HTTP/1.1");
            writer.println("Host: www.google.it");
            writer.println("");
            writer.flush();

            String s;
            while((s = reader.readLine()) != null)
                System.out.println(s);

            writer.close();
            reader.close();
            client.close();

        }
        catch (Exception ex)
        {

        }
    }
}
