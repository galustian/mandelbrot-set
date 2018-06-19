import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 750;
    private static final int ITERATIONS = 50;
    private static final float SCALE = 200;


    public static void main(String[] args) {
        var frame = new JFrame("Mandelbrot Set");

        var img = constructMandelbrotImage();

        var panel = new JComponent() {
            @Override
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        };

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private static BufferedImage constructMandelbrotImage() {
        var img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // Rules:
        // z0 = 0
        // f(z1) = z0^2 + c

        // c = a + bi
        // (a + bi)(a + bi) = a^2 - b^2 + 2abi
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                float x = (i - WIDTH / 2f) / SCALE;
                float y = (j - HEIGHT / 2f) / SCALE;

                int color = getPointColor(x, y);
                img.setRGB(i, j, color);
            }
        }

        return img;
    }

    /*private static int getPointColor(float x, float y) {
        float a_loop = x;
        float b_loop = y;

        float ac = a_loop;
        float bc = b_loop;

        int i = 0;
        for (; i < ITERATIONS; i++) {
            a_loop = a_loop * a_loop - b_loop * b_loop + ac;
            b_loop = 2*a_loop*b_loop + bc;

            if (a_loop*a_loop + b_loop*b_loop > 5) break;
        }

        if (i == ITERATIONS) return 0x000000;
        return Color.HSBtoRGB((float)i / ITERATIONS, 0.5f, 1);
    }*/

    private static int getPointColor(float x, float y) {
        float cx = x;
        float cy = y;

        int i = 0;

        for(; i < ITERATIONS; i++) {
            float nx = x*x - y*y + cx;
            float ny = 2*x*y + cy;
            x = nx;
            y = ny;

            if (x*x + y*y > 4) break;
        }

        if (i == ITERATIONS) return 0x000000;
        return Color.HSBtoRGB((float)i / ITERATIONS, 0.5f, 1);
    }
}
