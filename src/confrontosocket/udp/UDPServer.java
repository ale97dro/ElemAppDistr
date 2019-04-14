package confrontosocket.udp;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UDPServer implements Runnable{

    private static AtomicInteger ID_CLIENT = new AtomicInteger(0);

    private int port;

    public UDPServer(int port)
    {
        this.port = port;
    }

    private void serverService()
    {
        try
        {
            DatagramSocket server = new DatagramSocket(port);

            int byteCounter = 0;


            byte[] buf = new byte[2048];
            while (true)
            {
                //Wait for clients
                //Socket client = server.accept();
                //Thread.sleep(7000);
                //Pass the client to another thread, start it and come back to waiting for clients

                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                server.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                packet = new DatagramPacket(buf, buf.length, address, port);

                byteCounter += packet.getLength();
                System.out.println(byteCounter);

                String received = new String(packet.getData(), 0, packet.getLength());





                //System.out.println("Server: " + received);
                //System.out.println("Dal client ho ricevuto " + received);

                //Send packet to client
                //server.send(packet);
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
