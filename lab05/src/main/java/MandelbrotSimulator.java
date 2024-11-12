import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MandelbrotSimulator {
    private int threadNumber;
    private int taskNumber;
    private int maxIter;
    private int height;
    private int width;
    private int zoom;
    private BufferedImage image;

    public MandelbrotSimulator(int width, int height, int zoom, int iters, int threadNumber, int taskNumber) {
        this.threadNumber = threadNumber;
        this.taskNumber = taskNumber;
        this.maxIter = iters;
        this.height = height;
        this.width = width;
        this.zoom = zoom;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void simulate() {
        ExecutorService threadPool = Executors.newFixedThreadPool(threadNumber);
        List<Future<Integer>> futures = new ArrayList<>();

        if (taskNumber != (height * width)) {
            int pixelsPerTask = (height * width) / taskNumber;
            for (int i = 0; i < taskNumber; i++) {
                int start = i * pixelsPerTask;
                int end = (i == taskNumber - 1) ? (height * width) : start + pixelsPerTask;

                MandelbrotCalculator calculator = new MandelbrotCalculator(
                        start, end, width, height, maxIter, zoom, image
                );
                futures.add(threadPool.submit(calculator));
            }
        } else {
            for (int x = 0; x < (height * width); x++) {
                MandelbrotCalculator calculator = new MandelbrotCalculator(
                        x, x + 1, width, height, maxIter, zoom, image
                );
                futures.add(threadPool.submit(calculator));
            }
        }

        for (Future<Integer> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
    }

    public BufferedImage getImage() {
        return image;
    }
}
