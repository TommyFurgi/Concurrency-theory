import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BufferM2WithoutHunger implements BufferM2 {
    private int bufferSize;
    private int currentSize;
    private ReentrantLock blocker;
    private Condition producersNotifier;
    private Condition consumersNotifier;
    private Condition producerOnTheTableNotifier;
    private Condition consumerOnTheTableNotifier;

    private FileWriter fileWriter;

    public BufferM2WithoutHunger(int size, ReentrantLock blocker, FileWriter fileWriter) {
        this.bufferSize = size;
        this.blocker = blocker;
        this.producersNotifier = blocker.newCondition();
        this.consumersNotifier = blocker.newCondition();
        this.producerOnTheTableNotifier = blocker.newCondition();
        this.consumerOnTheTableNotifier = blocker.newCondition();
        this.currentSize = 0;
        this.fileWriter = fileWriter;
    }

    @Override
    public void put(int portion) throws InterruptedException {
        blocker.lock();
        try {
            long start = System.nanoTime();
            if (blocker.hasWaiters(producerOnTheTableNotifier)) {
                producersNotifier.await();
            }
            while (currentSize + portion > bufferSize) {
                if (!producerOnTheTableNotifier.await(200, TimeUnit.MILLISECONDS)) {
                    producersNotifier.signal();
                    return;
                }
            }
            long end = System.nanoTime();
            fileWriter.append("Producer," + portion + "," + (end - start) + "\n");
            fileWriter.flush();

            currentSize += portion;
            consumerOnTheTableNotifier.signal();
            producersNotifier.signal();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            blocker.unlock();
        }
    }

    @Override
    public void get(int portion) throws InterruptedException {
        blocker.lock();
        try {
            long start = System.nanoTime();
            if (blocker.hasWaiters(consumerOnTheTableNotifier)) {
                consumersNotifier.await();
            }
            while (this.currentSize - portion < 0) {
                if (!consumerOnTheTableNotifier.await(200, TimeUnit.MILLISECONDS)) {
                    consumersNotifier.signal();
                    return;
                }
            }
            long end = System.nanoTime();
            fileWriter.append("Consumer," + portion + "," + (end - start) + "\n");
            fileWriter.flush();

            currentSize -= portion;
            producerOnTheTableNotifier.signal();
            consumersNotifier.signal();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            blocker.unlock();
        }
    }
}
