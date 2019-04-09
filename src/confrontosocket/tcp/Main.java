package confrontosocket.tcp;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Esercizio  socket TCP");

        //Peer1 : ascolta su 8080, spedisce a 9090
        //Peer2 : ascolta su 9090, spedisce a 8080
        //IP: localhost

        TCPServer server = new TCPServer(8080);
        TCPClient client = new TCPClient("localhost", 8080);


        Thread serverThread = new Thread(server);
        Thread clientThread = new Thread(client);

        serverThread.start();
        clientThread.start();

        try
        {
            serverThread.join();
            clientThread.join();
        }
        catch(Exception ex)
        {

        }

//        TCPPeer peer1 = new TCPPeer(8080, 9090, "localhost");
//        TCPPeer peer2 = new TCPPeer(9090, 8080, "localhost");
//
//       // peer1.peerService();
//        //peer2.peerService();
//
//        Thread tP1 = new Thread(peer1);
//        Thread tP2 = new Thread(peer2);
//
//        tP1.start();
//        tP2.start();
//
//        try
//        {
//            tP1.join();
//            tP2.join();
//        }
//        catch (Exception ex)
//        {
//            System.out.println("Error!");
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
