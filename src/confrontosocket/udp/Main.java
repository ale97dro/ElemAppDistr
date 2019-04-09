package confrontosocket.udp;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Esercizio socket UDP");

        //Peer1 : ascolta su 8080, spedisce a 9090
        //Peer2 : ascolta su 9090, spedisce a 8080
        //IP: localhost
       // UDPPeer peer1 = new UDPPeer(8080, 9090, "localhost");
        //UDPPeer peer2 = new UDPPeer(9090, 8080, "localhost");

        Thread server = new Thread(new UDPServer(8080));
        Thread client = new Thread(new UDPClient("localhost", 8080));


        server.start();
        client.start();

        try
        {
            server.join();
            client.join();
        }
        catch (Exception ex)
        {
            System.out.println("Error!");
        }

        //TEST PER SEMPLICE APPLICAZIONE CLIENT-SERVER
//        UDPServer udpServer = new UDPServer(5000);
//        UDPClient udpClient = new UDPClient("localhost", 5000);
//
//        Thread server = new Thread(udpServer);
//        Thread client = new Thread(udpClient);
//
//        server.start();
//        client.start();
//
//
//        try
//        {
//            server.join();
//            client.join();
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }



//        Thread server = new Thread(new UDPServer("10.11.91.209"));
//        server.start();
//
//        Thread client1 = new Thread(new UDPClient("10.11.89.178"));
//        client1.start();
//
//        try
//        {
//            client1.join();
//            server.join();
//
//        }
//        catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
    }
}
