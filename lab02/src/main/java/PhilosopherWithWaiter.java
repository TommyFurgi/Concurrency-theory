public class PhilosopherWithWaiter extends Philosopher {
    private CountingSemaphore waiter;

    public PhilosopherWithWaiter(int philosopherId, Semaphore leftFork, Semaphore rightFork, CountingSemaphore waiter) {
        super(philosopherId, leftFork, rightFork);
        this.waiter = waiter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();

                waiter.block();
                rightFork.block();
                System.out.println("Philosopher " + philosopherId + " picked up right fork.");
                leftFork.block();
                System.out.println("Philosopher " + philosopherId + " picked up left fork.");
                waiter.release();

                eat();
                rightFork.release();
                leftFork.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
