public class ThreadIncrement extends Thread{
    private Counter counter;
    private Semaphore semaphore;

    public ThreadIncrement(Counter counter, Semaphore semaphore) {
        this.counter = counter;
        this.semaphore = semaphore;
    }

    public void run () {
        for (int i = 0; i < 100000000; i++) {
            semaphore.block();
            counter.increment();
            semaphore.release();
        }
    }
}
