package confrontosocket.udp;

public class UDPPeer implements Runnable{

    private int serverPort;
    private int clientPort;
    private String clientIp;

    /**
     * Costruttore per creare peers che eseguono sulla stessa macchina.
     * Fornisce la possibilità di specificare due porte diverse: una per il client e una per il server.
     * @param serverPort Porta su cui ascolta il server
     * @param clientPort Porta su cui spedisce il client
     * @param clientIp Ip a cui il client spedisce
     */
    public UDPPeer(int serverPort, int clientPort, String clientIp)
    {
        this.serverPort = serverPort;
        this.clientPort = clientPort;
        this.clientIp = clientIp;
    }

    /**
     * Costruttore per peers in esecuzione su macchine diverse.
     * @param commonPort Porta su cui avviene la comunicazione: il server è in ascolto su questa porta e il client manda a questa porta
     * @param ip Indirizzo IP del destinatario
     */
    public UDPPeer(int commonPort, String ip)
    {
        this.serverPort = this.clientPort = commonPort;
        this.clientIp = ip;
    }

    private void peerService()
    {
        Thread server = new Thread(new UDPServer(serverPort));
        server.start();

        Thread client1 = new Thread(new UDPClient(clientIp, clientPort));
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
