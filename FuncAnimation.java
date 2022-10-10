import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class FuncAnimation extends JPanel{
    int[] coord = {65, 20, 40, 80};
    int marg = 60;

    protected void paintComponent(Graphics grf) {
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D)grf;
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        graph.draw(new Line2D.Double(marg, marg, marg, height-marg));
        graph.draw(new Line2D.Double(marg, height-marg, width-marg, height-marg));

        double x = (double)(width-(2*marg))/(coord.length-1);
        double scale = (double)(height-(2*marg))/getMax();

        graph.setPaint(Color.RED);

        for(int i = 0; i < coord.length; i++) {
            double x1 = marg+(i*x);
            double y1 = height-marg-(scale*coord[i]);
            graph.fill(new Ellipse2D.Double(x1-2, y1-2, 4, 4));
        }
    }

    private int getMax() {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < coord.length; i++) {
            if(coord[i] > max) {
                max = coord[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new FuncAnimation());
        frame.setSize(400,400);
        frame.setLocation(200,200);
        frame.setVisible(true);
    }
}
