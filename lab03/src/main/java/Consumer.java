public class Consumer implements Runnable {
    private Waiter waiter;
    private int id;

    public Consumer(Waiter waiter, int id) {
        this.waiter = waiter;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((int) Math.floor(Math.random() * 10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("User from pair: " + this.id + " wants table");
            try {
                waiter.bookTable(this.id);
                System.out.println("User from pair: " + this.id + " eats");
                Thread.sleep((int) Math.floor(Math.random() * 5000));
                System.out.println("User from pair: " + this.id + " ends eating");
                waiter.releaseTable();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
