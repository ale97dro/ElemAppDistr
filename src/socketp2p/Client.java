package socketp2p;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Runnable{

    private static AtomicInteger ID_CLIENT = new AtomicInteger(0);

    private String ipAddress;
    private int port;

    public Client(String ipAddress, int port)
    {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void clientService()
    {
        Socket client;
        while (true)
        {
            try
            {
                client = new Socket(ipAddress, port);
                client.setSoTimeout(1000);
                break;
            } catch (IOException e)
            {
                System.out.println("Connection creation error");
            }
        }

        try
        {
            //Create client socket and get I/O streams
//            client.connect(client.getRemoteSocketAddress(), 1000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            //Write to server
            writer.println("Hello server, I'm client " + ID_CLIENT.incrementAndGet());
            writer.flush(); //Communication "yield"

            //Wait for server response and then write it
            String response = reader.readLine();

            System.out.println("Server response: " + response);

            reader.close();
            writer.close();
            client.close();
        }
        catch (SocketTimeoutException exto)
        {
            System.out.println("Timeout");
        }
        catch (Exception ex)
        {
            System.out.println("Connection error");
        }

    }

    @Override
    public void run()
    {
        clientService();
    }
}
