/* Based on code from this thread: https://stackoverflow.com/a/19914865 */
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WavePlotter extends JFrame {

    public WavePlotter() {
        setLayout(new BorderLayout());
        add(new DrawFunc(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        WavePlotter frame = new WavePlotter();
        frame.setSize(400, 300);
        frame.setTitle("WavePlotter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class DrawFunc extends JPanel {

        double sin(double x) {
            return Math.sin(x);
        }

        double cos(double y) {
            return Math.cos(y);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawLine(10, 100, 380, 100); // x axis
            g.drawLine(200, 30, 200, 190); // y axis

            g.drawString("X", 360, 80);
            g.drawString("Y", 220, 40);
            g.drawString("0", 200, 115);
            // g.drawString("-2\u03c0", 95, 115);
            // g.drawString("2\u03c0", 305, 115);

            Polygon sineWave = new Polygon();
            Polygon cosineWave = new Polygon();

            for (int x = -170; x <= 170; x++) {
                sineWave.addPoint(x + 200, 100 - (int) (50 * sin((x / 100.0) * 2 * Math.PI)));
            }

            for (int x = -170; x <= 170; x++) {
                cosineWave.addPoint(x + 200, 100 - (int) (50 * cos((x / 100.0) * 2 * Math.PI)));
            }

            g.setColor(Color.red);
            g.drawPolyline(sineWave.xpoints, sineWave.ypoints, sineWave.npoints);

            g.setColor(Color.blue);
            g.drawPolyline(cosineWave.xpoints, cosineWave.ypoints, cosineWave.npoints);
        }
    }
}