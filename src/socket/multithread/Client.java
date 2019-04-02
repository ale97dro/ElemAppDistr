package socket.multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Runnable{

    private static AtomicInteger ID_CLIENT = new AtomicInteger(0);

    public void clientService()
    {
        try
        {
            //Create client socket and get I/O streams
            Socket client = new Socket("localhost", 5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            //Write to server
            writer.println("Hello server, I'm client " + ID_CLIENT.incrementAndGet());
            writer.flush(); //Communication "yield"

            //Wait for server response and then write it
            String response = reader.readLine();

            System.out.println("UDPServer response: " + response);

            reader.close();
            writer.close();
            client.close();
        }
        catch (Exception ex)
        {

        }
    }

    @Override
    public void run()
    {
        clientService();
    }
}
