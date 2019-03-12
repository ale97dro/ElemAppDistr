package socket.base;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Esercizio socket");

        Thread server = new Thread(new Server());
        server.start();

        Thread client = new Thread(new Client());
        client.start();


        try
        {
            server.join();
            client.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
