import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterMonitor {
    private final Lock lock = new ReentrantLock();
    private Condition condition;
    private int printersNumber;
    private boolean[] isPrinterLocked;

    public PrinterMonitor(int printersNumber) {
        this.condition = lock.newCondition();
        this.printersNumber = printersNumber;
        this.isPrinterLocked = new boolean[printersNumber];
    }

    public int getPrinter() throws InterruptedException  {
        lock.lock();
        try {
            while (this.printersNumber <= 0) {
                condition.await();
            }
            int printer_id = -1;
            for (int i = 0; i < isPrinterLocked.length; i++) {
                if (!isPrinterLocked[i]) {
                    isPrinterLocked[i] = true;
                    printersNumber--;
                    printer_id = i;
                    break;
                }
            }
            return printer_id;
        } finally {
            lock.unlock();
        }
    }

    public void release(int id) {
        lock.lock();
        try {
            isPrinterLocked[id] = false;
            printersNumber++;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
