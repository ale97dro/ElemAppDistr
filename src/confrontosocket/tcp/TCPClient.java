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

            long start = System.currentTimeMillis();
            client = new Socket(ipAddress, port);
            //PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            DataOutputStream writer = new DataOutputStream(client.getOutputStream());

            for(int i = 0; i < 100_000; i++)
            {
                //byte[] buf = "ciao server".getBytes();

                byte[] buf = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat".getBytes();

                writer.write(buf);
                writer.flush(); //Communication "yield"
            }

            writer.close();
            client.close();

            long end = System.currentTimeMillis();

            System.out.println("Time: " + (end - start));
        }
        catch (IOException e)
        {
            //System.out.println("Connection creation error");
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        clientService();
    }
}
