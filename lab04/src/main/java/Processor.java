public class Processor implements Runnable {
    protected Buffer buffer;
    protected int id;

    public Processor(Buffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        while(true) {
            for (int i = 0; i < buffer.getSize(); i++) {
                method(i);
            }
        }
    }

    public void method(int i) {
        try {
            buffer.startProcess(i, id);
            Thread.sleep((int) Math.floor(Math.random() * 1000));
            buffer.endProcess(i, id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
