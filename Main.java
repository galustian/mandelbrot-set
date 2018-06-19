import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 750;
    private static final int ITERATIONS = 200;
    public static final float SCALE = 200;


    public static void main(String[] args) {
        var frame = new JFrame("Mandelbrot Set");

        var img = constructMandelbrotImage();

        var panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        };

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.pack();
        frame.add(panel);
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

                Color color = getPointColor(x, y);
                img.setRGB(i, j, color.getRGB());
            }
        }

        return img;
    }

    private static Color getPointColor(float x, float y) {
        float a_loop = x;
        float b_loop = y;

        float ac = a_loop;
        float bc = b_loop;

        int k = 0;
        for (; k < ITERATIONS; k++) {
            a_loop = a_loop * a_loop - b_loop * b_loop + ac;
            b_loop = 2*a_loop*b_loop + bc;

            if (a_loop*a_loop + b_loop*b_loop > 5) break;
        }

        if (k == ITERATIONS) return new Color(0, 0, 0);
        return new Color(255, 255, 255);
    }
}
