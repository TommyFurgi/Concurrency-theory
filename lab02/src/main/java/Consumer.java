public class Consumer implements Runnable {
    private Semaphore semaphore;
    int clientId;

    public Consumer(int id, Semaphore semaphore) {
        this.semaphore = semaphore;
        this.clientId = id;
    }

    public void run() {
        semaphore.block();
        System.out.println("Client with id: " + this.clientId + " buying");
        try {
            Thread.sleep(Math.round((2 + Math.random() * 5) * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Client with id: " + this.clientId + " ends");
        semaphore.release();
    }
}