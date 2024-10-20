public class PhilosopherTrivial extends Philosopher {

    public PhilosopherTrivial(int philosopherId, Semaphore leftFork, Semaphore rightFork) {
        super(philosopherId, leftFork, rightFork);
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                rightFork.block();
                System.out.println("Philosopher " + philosopherId + " picked up right fork.");
                leftFork.block();
                System.out.println("Philosopher " + philosopherId + " picked up left fork.");
                eat();
                rightFork.release();
                leftFork.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}