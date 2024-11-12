import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class MandelbrotCalculator implements Callable<Integer> {
    private int width;
    private int height;
    private int emdIndex;
    private int startIndex;
    private final int MAX_ITER;
    private final double ZOOM;
    private BufferedImage image;

    public MandelbrotCalculator(int startIndex, int emdIndex, int width, int height, int maxIter, int zoom, BufferedImage image) {
        this.width = width;
        this.height = height;
        this.startIndex = startIndex;
        this.emdIndex = emdIndex;
        this.MAX_ITER = maxIter;
        this.ZOOM = zoom;
        this.image = image;
    }

    @Override
    public Integer call() {
        double zx, zy, cX, cY, tmp;


        for (int i = startIndex; i < emdIndex; i++) {
            int x = i % width;
            int y = i / width;

            zx = zy = 0;
            cX = (x - (double) this.width / 2) / this.ZOOM;
            cY = (y - (double) this.height / 2) / this.ZOOM;
            int iter = MAX_ITER;
            while (zx * zx + zy * zy < 4 && iter > 0) {
                tmp = zx * zx - zy * zy + cX;
                zy = 2.0 * zx * zy + cY;
                zx = tmp;
                iter--;
            }
//            this.image.setRGB(x, y, iter | (iter << 8));
        }


        return 0;
    }
}