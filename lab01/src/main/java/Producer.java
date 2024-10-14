public class Producer implements Runnable {
    private Buffer buffer;
    private int num;

    public Producer(Buffer buffer, int num) {
        this.buffer = buffer;
        this.num = num;
    }

    public void run() {

        for(int i = 0;  i < 3;   i++) {
            buffer.put("Producer nr " + num + " message nr " + i);
        }

    }
}
