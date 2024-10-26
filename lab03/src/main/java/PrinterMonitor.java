import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterMonitor {
    private final Lock lock = new ReentrantLock();
    private Condition printers;
    private int printersNumber;
    private boolean[] isPrinterLocked;

    public PrinterMonitor(int printersNumber) {
        this.printers = lock.newCondition();
        this.printersNumber = printersNumber;
        this.isPrinterLocked = new boolean[printersNumber];
    }

    public int getPrinter() throws InterruptedException  {
        lock.lock();
        try {
            while (this.printersNumber <= 0) {
                printers.await();
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
            printers.signal();
        } finally {
            lock.unlock();
        }
    }
}
