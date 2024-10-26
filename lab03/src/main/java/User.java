public class User implements Runnable {
    private PrinterMonitor monitor;
    private int id;

    public User(PrinterMonitor monitor, int id) {
        this.monitor = monitor;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("User with id: " + this.id + " wants print");
            try {
                int printer = monitor.getPrinter();
                System.out.println("User with id: " + this.id + " uses printer with id: " + printer);
                Thread.sleep((int) Math.floor(Math.random() * 10000));
                System.out.println("User with id: " + this.id + " ends printing");
                monitor.release(printer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep((int) Math.floor(Math.random() * 10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
