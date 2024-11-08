    import java.util.Random;

    public class ProducerM2 implements Runnable {
        private int id;
        private BufferM2 buffer;
        private int maxSize;

        public ProducerM2(BufferM2 buffer, int id, int size) {
            this.id = id;
            this.buffer = buffer;
            this.maxSize = size;
        }

        @Override
        public void run() {
            Random rand = new Random();

            try {
                int portion = rand.nextInt(maxSize) + 1;
                buffer.put(portion);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
