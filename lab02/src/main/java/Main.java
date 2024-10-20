import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        binSemMain();
//        countSemMain();

//        philosopherTrivialMain();
//        philosopherAsymetricMain();
        philosopherWithWaiterMain();
    }

    public static void binSemMain() throws InterruptedException {
        Counter counter = new Counter();
        BinarySemaphore semaphore = new BinarySemaphore();
        ThreadIncrement incrementor = new ThreadIncrement(counter, semaphore);
        ThreadDecrement decrementor = new ThreadDecrement(counter, semaphore);

        incrementor.start();
        decrementor.start();

        incrementor.join();
        decrementor.join();

        System.out.println(counter.num);
    }

    public static void countSemMain() throws InterruptedException {
        CountingSemaphore semaphore = new CountingSemaphore(2);
        Consumer c1 = new Consumer(1, semaphore);
        Consumer c2 = new Consumer(2, semaphore);
        Consumer c3 = new Consumer(3, semaphore);
        Consumer c4 = new Consumer(4, semaphore);
        Consumer c5 = new Consumer(5, semaphore);
        Consumer c6 = new Consumer(6, semaphore);

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);
        Thread t4 = new Thread(c4);
        Thread t5 = new Thread(c5);
        Thread t6 = new Thread(c6);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

    public static void philosopherTrivialMain() throws InterruptedException {
        int numPhilosophers = 5;
        BinarySemaphore[] forks = new BinarySemaphore[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new BinarySemaphore();
        }
        for (int i = 0; i < numPhilosophers; i++) {
            final int id = i;
            Philosopher philosopher = new PhilosopherTrivial(id, forks[id], forks[(id + 1) % numPhilosophers]);
            philosopher.start();
        }
    }

    public static void philosopherAsymetricMain() throws InterruptedException {
        int numPhilosophers = 5;
        BinarySemaphore[] forks = new BinarySemaphore[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new BinarySemaphore();
        }
        for (int i = 0; i < numPhilosophers; i++) {
            final int id = i;
            Philosopher philosopher = new PhilosopherAsymetric(id, forks[id], forks[(id + 1) % numPhilosophers]);
            philosopher.start();
        }
    }

    public static void philosopherWithWaiterMain() throws InterruptedException {
        int numPhilosophers = 5;
        CountingSemaphore waiter = new CountingSemaphore(numPhilosophers - 1);
        BinarySemaphore[] forks = new BinarySemaphore[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new BinarySemaphore();
        }
        for (int i = 0; i < numPhilosophers; i++) {
            final int id = i;
            Philosopher philosopher = new PhilosopherWithWaiter(id, forks[id], forks[(id + 1) % numPhilosophers], waiter);
            philosopher.start();
        }
    }

}
