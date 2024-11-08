public class Consumer extends Processor {
    public Consumer(Buffer buffer, int id) {
        super(buffer, id);
    }

    @Override
    public void method(int i) {
        try {
            this.buffer.startProcess(i, id);
            Thread.sleep((int) Math.floor(Math.random() * 1000));
            System.out.println("Consumer have eaten cell nr " + i);
            buffer.endProcess(i, id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
