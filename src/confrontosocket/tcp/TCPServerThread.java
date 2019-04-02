package confrontosocket.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPServerThread implements Runnable {

    private Socket client;
    private int id;

    public TCPServerThread(Socket client, int id)
    {
        this.client = client;
        this.id = id;
    }

    @Override
    public void run()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            String welcome = reader.readLine();
            System.out.println(welcome);
            writer.println("Hello client! Here you're message: \"" + welcome + "\"");
            writer.flush();

            reader.close();
            writer.close();
            client.close();
        }
        catch (Exception ex )
        {

        }
    }
}
