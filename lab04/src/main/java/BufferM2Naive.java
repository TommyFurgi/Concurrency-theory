import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class BufferM2Naive implements BufferM2 {
    private int bufferSize;
    private int currentSize;
    private Lock blocker;
    private Condition producersNotifier;
    private Condition consumersNotifier;
    private FileWriter fileWriter;

    public BufferM2Naive(int size, Lock blocker, FileWriter fileWriter) {
        this.bufferSize = size;
        this.blocker = blocker;
        this.producersNotifier = blocker.newCondition();
        this.consumersNotifier = blocker.newCondition();
        this.currentSize = 0;
        this.fileWriter = fileWriter;
    }

    @Override
    public void put(int portion) throws InterruptedException {
        blocker.lock();
        try {
            long start = System.nanoTime();
            while (this.currentSize + portion > this.bufferSize) {
                if (!producersNotifier.await(200, TimeUnit.MILLISECONDS)) {
                    return;
                }
            }
            long end = System.nanoTime();
            fileWriter.append("Producer," + portion + "," + (end - start) + "\n");
            fileWriter.flush();

            currentSize += portion;
            consumersNotifier.signalAll();
            producersNotifier.signalAll();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            blocker.unlock();
        }
    }

    @Override
    public void get(int portion) throws InterruptedException {
        blocker.lock();
        long start = 0;
        long end = 0;
        try {
            start = System.nanoTime();
            while (this.currentSize - portion < 0) {
                if (!consumersNotifier.await(200, TimeUnit.MILLISECONDS)) {
                    return;
                }
            }
            end = System.nanoTime();
            fileWriter.append("Consumer," + portion + "," + (end - start) + "\n");
            fileWriter.flush();

            currentSize -= portion;
            producersNotifier.signalAll();
            consumersNotifier.signalAll();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            blocker.unlock();
        }
    }
}