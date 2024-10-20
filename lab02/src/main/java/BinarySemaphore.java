public class BinarySemaphore extends Semaphore {
    private boolean value = true;

    @Override
    public synchronized void block() {
        while (!value) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value = false;
    }

    @Override
    public synchronized void release() {
        value = true;
        notify();
    }
}