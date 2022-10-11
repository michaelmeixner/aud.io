import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
public class FuncAnimation extends JPanel{
    int[] coord;
    int margin = 60;
    
    // public FuncAnimation() {}

    // public FuncAnimation(int[] coords) {
    //     coord = coords;
    // }
    
    protected void paintComponent(Graphics grf) {
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D)grf;
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();
        
        graph.draw(new Line2D.Double(margin, margin, margin, height-margin));
        graph.draw(new Line2D.Double(margin, height-margin, width-margin, height-margin));
        
        double x = (double)(width-(2*margin))/(coord.length-1);
        double scale = (double)(height-(2*margin))/getMax();
        
        graph.setPaint(Color.RED);
        
        for(int i = 0; i < coord.length; i++) {
            double x1 = margin+(i*x);
            double y1 = height-margin-(scale*coord[i]);
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
    
    public void display() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new FuncAnimation());
        frame.setSize(400,400);
        frame.setLocation(200,200);
        frame.setVisible(true);
    }
}
