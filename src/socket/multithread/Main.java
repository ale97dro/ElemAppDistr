package socket.multithread;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Esercizio socket");

        Thread server = new Thread(new Server());
        server.start();

        Thread client1 = new Thread(new Client());
        Thread client2 = new Thread(new Client());
        Thread client3 = new Thread(new Client());

        client2.start();
        client1.start();
        client3.start();

        try
        {
            client1.join();
            client2.join();
            client3.join();
            server.join();

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
