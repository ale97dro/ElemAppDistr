package confrontosocket.udp;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UDPClient implements Runnable{

    private static AtomicInteger ID_CLIENT = new AtomicInteger(0);

    private String ipAddress;
    private int port;

    public UDPClient(String ipAddress, int port)
    {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    private void clientService()
    {
        DatagramSocket client;

        try
        {
            long start = System.currentTimeMillis();

            client = new DatagramSocket();

            for(int i = 0; i < 100_000; i++)
            {
                byte[] buf;

                //buf = "ciao server".getBytes();

                buf = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat".getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), port);
                client.send(packet);

                //receive packets from server
//                packet = new DatagramPacket(buf, buf.length);
//                client.receive(packet);
//
//                String received = new String(packet.getData(), 0, packet.getLength()); //packet.getLenght() -> lunghezza totale del pacchetto

                //System.out.println(i);
            }

            long end = System.currentTimeMillis();

            System.out.println("Time: " + (end - start));

        } catch (IOException e) {
            e.printStackTrace();
        }


//        while (true)
//        {
//            try
//            {
//
////                client.setSoTimeout(1000);
//                break;
//            } catch (IOException e)
//            {
//                System.out.println("Connection creation error");
//            }
//        }
//
//        try
//        {
//            //Create client socket and get I/O streams
////            client.connect(client.getRemoteSocketAddress(), 1000);
//
//
//
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            PrintWriter writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
//
//            //Write to server
//            writer.println("Hello server, I'm client " + ID_CLIENT.incrementAndGet());
//            writer.flush(); //Communication "yield"
//
//            //Wait for server response and then write it
//            String response = reader.readLine();
//
//            System.out.println("UDPServer response: " + response);
//
//            reader.close();
//            writer.close();
//            client.close();
//        }
//        catch (SocketTimeoutException exto)
//        {
//            System.out.println("Timeout");
//        }
//        catch (Exception ex)
//        {
//            System.out.println("Connection error");
//        }

    }

    @Override
    public void run()
    {
        clientService();
    }
}
