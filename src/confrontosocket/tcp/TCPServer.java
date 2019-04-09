package confrontosocket.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class TCPServer implements Runnable{

    //private static AtomicInteger ID_CLIENT = new AtomicInteger(0);

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

                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                //PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

               // DataInputStream reader = new DataInputStream(client.getInputStream());


                //https://stackoverflow.com/questions/1176135/socket-send-and-receive-byte-array


                String line;

                int byteCounter = 0;

                while((line = reader.readLine()) != null)
                {
                   // System.out.println(line);
                    byteCounter += line.length();
                }


//                int length = reader.readInt();
//                if(length > 0)
//                {
//                    byte[] buf = new byte[length]; //finisce la memoria
//                    //reader.readFully(buf, 0, buf.length);
//                }

                reader.close();
                client.close();

                System.out.println(byteCounter);
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
