import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Buffer {
    private int[] productionLine;
    private Lock blocker;
    private Condition[] notifiers;

    public Buffer(int size, Lock blocker, Condition[] notifiers) {
        this.productionLine = new int[size];
        this.blocker = blocker;
        this.notifiers = notifiers;
        for (int i = 0; i < size; i++) {
            productionLine[i] = -1;
        }
    }

    public void startProcess(int cell, int processorId) throws InterruptedException {
        blocker.lock();
        try {
            while (productionLine[cell] != processorId-1) {
                notifiers[processorId - 1].await();
            }
            System.out.println("Processor with id: " + processorId + " got the cell " + cell);
        } finally {
            blocker.unlock();
        }
    }

    public void endProcess(int cell, int processorId) {
        blocker.lock();
        try {
            if(processorId < 6) {
                productionLine[cell] = processorId;
                notifiers[processorId].signal();
            } else if (processorId == 6) {
                productionLine[cell] = -1;
            }
        } finally {
            blocker.unlock();
        }
    }

    public int getSize() {
        return productionLine.length;
    }
}
