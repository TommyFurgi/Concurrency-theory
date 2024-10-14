public class Consumer implements Runnable {
    private Buffer buffer;
    private int num;

    public Consumer(Buffer buffer, int num) {
        this.buffer = buffer;
        this.num = num;
    }

    public void run() {

        for(int i = 0;  i < 3;   i++) {
            String message = buffer.take();
//            System.out.println("Consumer nr " + num + " got message: "+ message);
        }

    }
}
