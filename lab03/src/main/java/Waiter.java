import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {
    private final Lock lock = new ReentrantLock();
    private Condition[] pairs;
    private Condition occupation;
    private int consumersOnTable;
    private int[] waiting;


    public Waiter(int pairsNumber) {
        this.occupation = lock.newCondition();
        this.pairs = new Condition[pairsNumber];
        this.consumersOnTable = 0;
        this.waiting = new int[pairsNumber];
        for (int i = 0; i < pairsNumber; i++) {
            pairs[i] = lock.newCondition();
            waiting[i] = 0;
        }
    }

    public void bookTable(int number) throws InterruptedException  {
        lock.lock();
        try {
            while (consumersOnTable > 0) {
                occupation.await();
            }
            if (waiting[number] == 0) {
                waiting[number] = 1;
                pairs[number].await();

            } else {
                waiting[number] = 0;
                consumersOnTable = 2;
                pairs[number].signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void releaseTable() {
        lock.lock();
        try {
            consumersOnTable--;
            if (consumersOnTable == 0) {
                occupation.signalAll();
            }

        } finally {
            lock.unlock();
        }
    }
}
