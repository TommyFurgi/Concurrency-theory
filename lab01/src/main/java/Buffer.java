public class Buffer {
    private String message = "";


    public synchronized String take () {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String buffer = this.message;
        this.message = "";
        System.out.println("Buffer LOST message: " + buffer);
        notifyAll();
        return buffer;
    }

    public synchronized void put (String buffer) {
        while (!isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Buffer GOT message: " + buffer);
        this.message = buffer;
        notifyAll();

    }

    public boolean isEmpty() {
        return this.message == "";
    }

}
