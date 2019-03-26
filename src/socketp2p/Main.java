package socketp2p;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Esercizio socket p2p");

        Peer node = new Peer(5000, "10.11.89.178");

        node.peerService();

//        Thread server = new Thread(new Server("10.11.91.209"));
//        server.start();
//
//        Thread client1 = new Thread(new Client("10.11.89.178"));
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
