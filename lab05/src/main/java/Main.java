import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        int coreNo = Runtime.getRuntime().availableProcessors();
        int[] threadNumbers = { 1, coreNo, 4 * coreNo };
        int[] maxIters = { 100, 1000, 10000 };

        final int runs = 10;
        final int width = 400;
        final int height = 400;
        final int zoom = 150;

        FileWriter fileWriter = new FileWriter("./docs/results.csv");

        for (int iters : maxIters) {
            for (int threadNumber : threadNumbers) {
                int[] taskNumbers = { threadNumber, 10 * threadNumber, width * height };

                for (int taskNumber : taskNumbers) {
                    ArrayList<Long> times = new ArrayList<>();

                    for (int i=0; i<runs; i++) {
                        MandelbrotSimulator mandelbrotSimulator = new MandelbrotSimulator(
                                width, height, zoom, iters, threadNumber, taskNumber
                                );
                        long startTime = System.nanoTime();
                        mandelbrotSimulator.simulate();
                        times.add(System.nanoTime() - startTime);

//                        BufferedImage image = mandelbrotSimulator.getImage();
//                        saveImage(image, iters, threadNumber, taskNumber);
                    }

                    int avgTime = (int) mean(times);
                    int devTime = (int) dev(times, avgTime);

                    fileWriter.append(
                            iters + "," + threadNumber + "," + taskNumber + "," + avgTime + "," + devTime+"\n");
                    fileWriter.flush();
                }
            }
        }
    }

    private static double mean(List<Long> times) {
        if (times.isEmpty())
            return 0;

        double sum = 0;
        for (Long num : times)
            sum += num;

        return sum / times.size();
    }

    private static double dev(List<Long> times, double mean) {
        return Math.sqrt(1.0 * (times.stream().mapToDouble(i -> (i - mean) * (i - mean)).sum()) / (times.size()));
    }

    private static void saveImage (BufferedImage image, int iters, int threadNumber, int taskNumber) {
        String filename = String.format(
                "./docs/%d/mandelbrot_threads-%d_tasks-%d.png", iters, threadNumber, taskNumber);
        File outputDir = new File("./docs/" + iters);
        if (!outputDir.exists()) {
            boolean dirsCreated = outputDir.mkdirs();
            if (!dirsCreated) {
                return;
            }
        }
        try {
            File outputfile = new File(filename);
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
