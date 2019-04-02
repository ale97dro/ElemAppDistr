package confrontosocket.tcp;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public class TCPClient implements Runnable{

    private static AtomicInteger ID_CLIENT = new AtomicInteger(0);

    private String ipAddress;
    private int port;

    public TCPClient(String ipAddress, int port)
    {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void clientService()
    {
        Socket client;

        try
        {
            //BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            client = new Socket(ipAddress, port);
            //PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            DataOutputStream writer = new DataOutputStream(client.getOutputStream());

            for(int i = 0; i < 100_000; i++)
            {
                byte[] buf = "ciao server".getBytes();
                //Write to server
               // writer.println("Hello server, I'm client " + ID_CLIENT.incrementAndGet());
                writer.write(buf);
                writer.flush(); //Communication "yield"

            }
           // System.out.println("Server response: " + response);

            //reader.close();

            writer.close();
            client.close();
            //client.setSoTimeout(1000);
        }
        catch (IOException e)
        {
            System.out.println("Connection creation error");
        }
    }

    @Override
    public void run()
    {
        clientService();
    }
}
