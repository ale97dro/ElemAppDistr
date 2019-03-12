package socket.multithread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private static AtomicInteger ID_CLIENT = new AtomicInteger(0);

    public void serverService()
    {
        try {
            ServerSocket server = new ServerSocket(5000);

            while (true)
            {
                Socket client = server.accept();

                Thread clientThread = new Thread(new ServerThread(client, ID_CLIENT.incrementAndGet()));

                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run()
    {
        serverService();
    }
}
