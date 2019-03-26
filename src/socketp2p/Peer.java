package socketp2p;

public class Peer {

    private int serverPort;
    private String clientIp;

    public Peer(int serverPort, String clientIp)
    {
        this.serverPort = serverPort;
        this.clientIp = clientIp;
    }

    public void peerService()
    {
        Thread server = new Thread(new Server(serverPort));
        server.start();

        Thread client1 = new Thread(new Client(clientIp, serverPort));
        client1.start();

        try
        {
            client1.join();
            server.join();

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
