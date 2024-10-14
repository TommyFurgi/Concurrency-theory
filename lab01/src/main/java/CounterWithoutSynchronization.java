public class CounterWithoutSynchronization extends Counter {
    public void increment() {
        num += 1;
    }

    public void decrement() {
        num -= 1;
    }
}
