package socketp2p;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

    private Socket client;
    private int id;

    public ServerThread(Socket client, int id)
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
            writer.println("Ciao Bertak");
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
