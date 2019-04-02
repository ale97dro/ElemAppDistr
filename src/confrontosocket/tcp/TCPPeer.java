package confrontosocket.tcp;

public class TCPPeer implements Runnable{

    private int serverPort;
    private int clientPort;
    private String clientIp;

    /**
     *
     * @param serverPort Porta su cui ascolta il server
     * @param clientPort Porta su cui spedisce il client
     * @param clientIp Ip a cui il client spedisce
     */
    public TCPPeer(int serverPort, int clientPort, String clientIp)
    {
        this.serverPort = serverPort;
        this.clientPort = clientPort;
        this.clientIp = clientIp;
    }

    public TCPPeer(int commonPort, String ip)
    {
        this.serverPort = this.clientPort = commonPort;
        this.clientIp = ip;
    }

    public void peerService()
    {
        Thread server = new Thread(new TCPServer(serverPort));
        server.start();

        Thread client1 = new Thread(new TCPClient(clientIp, clientPort));
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

    @Override
    public void run()
    {
        peerService();
    }
}
