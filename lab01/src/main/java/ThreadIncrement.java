public class ThreadIncrement extends Thread{
    private Counter counter;

    public ThreadIncrement(Counter counter) {
        this.counter = counter;
    }

    public void run () {
        for (int i = 0; i < 100000000; i++) {
            counter.increment();
        }
    }
}
