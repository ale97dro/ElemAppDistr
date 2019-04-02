package confrontosocket.tcpPeer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class TCPServer implements Runnable{

    private static AtomicInteger ID_CLIENT = new AtomicInteger(0);

    private int port;

    public TCPServer(int port)
    {
        this.port = port;
    }

    public void serverService()
    {
        try
        {
            ServerSocket server = new ServerSocket(port);

            while (true)
            {
                //Wait for clients
                Socket client = server.accept();
                //Thread.sleep(7000);
                //Pass the client to another thread, start it and come back to waiting for clients
                Thread clientThread = new Thread(new TCPServerThread(client, ID_CLIENT.incrementAndGet()));
                clientThread.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


    @Override
    public void run()
    {
        serverService();
    }
}
