public class CounterWithSynchronization extends Counter{
    public synchronized void increment() {
        num += 1;
    }

    public synchronized void decrement() {
        num -= 1;
    }
}
