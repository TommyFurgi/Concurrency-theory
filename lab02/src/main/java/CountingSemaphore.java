public class CountingSemaphore extends Semaphore {
    private int counter;

    public CountingSemaphore(int counter) {
        this.counter = counter;
    }

    @Override
    public synchronized void block() {
        while (counter <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter -= 1;
    }

    @Override
    public synchronized void release() {
        counter += 1;
        notify();
    }
}
