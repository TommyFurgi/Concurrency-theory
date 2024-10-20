public class ThreadDecrement extends Thread{
    private Counter counter;
    private Semaphore semaphore;

    public ThreadDecrement(Counter counter, Semaphore semaphore) {
        this.counter = counter;
        this.semaphore = semaphore;
    }

    public void run () {
        for (int i = 0; i < 100000000; i++) {
            semaphore.block();
            counter.decrement();
            semaphore.release();
        }
    }
}
