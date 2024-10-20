public class PhilosopherAsymetric extends Philosopher {
    public PhilosopherAsymetric(int philosopherId, Semaphore leftFork, Semaphore rightFork) {
        super(philosopherId, leftFork, rightFork);
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                if (philosopherId % 2 == 0) {
                    rightFork.block();
                    System.out.println("Philosopher " + philosopherId + " picked up right fork.");
                    leftFork.block();
                    System.out.println("Philosopher " + philosopherId + " picked up left fork.");
                } else {
                    leftFork.block();
                    System.out.println("Philosopher " + philosopherId + " picked up left fork.");
                    rightFork.block();
                    System.out.println("Philosopher " + philosopherId + " picked up right fork.");
                }
                eat();
                rightFork.release();
                leftFork.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
