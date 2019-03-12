package socket.base;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    public void serverService()
    {
        try {
            ServerSocket server = new ServerSocket(5000);

            while (true)
            {
                Socket client = server.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

                String welcome = reader.readLine();
                System.out.println(welcome);
                writer.println(welcome);
                writer.flush();

                reader.close();
                writer.close();
                client.close();
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
