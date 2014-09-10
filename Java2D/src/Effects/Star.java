package Effects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

class Surface2 extends JPanel
        implements ActionListener {

    private double points[][] = {
        {0, 85}, {75, 75}, {100, 10}, {125, 75},
        {200, 85}, {150, 125}, {160, 190}, {100, 150},
        {40, 190}, {50, 125}, {0, 85}
    };
    
    private Timer timer;
    private double angle = 0;
    private double scale = 1;
    private double delta = 0.01;

    public Surface2() {

        initTimer();
    }
    
    private void initTimer() {
        
        timer = new Timer(10, this);
        timer.start();        
    }

    private void doDrawing(Graphics g) {
        
        int h = getHeight();
        int w = getWidth();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.translate(w / 2, h / 2);
        GeneralPath star = new GeneralPath();
        star.moveTo(points[0][0], points[0][1]);

        for (int k = 1; k < points.length; k++) {
            
            star.lineTo(points[k][0], points[k][1]);
        }

        star.closePath();
        g2d.rotate(angle);
        g2d.scale(scale, scale);

        g2d.fill(star);        
    }
    
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (scale < 0.01) {
            
            delta = -delta;
        } else if (scale > 0.99) {
            
            delta = -delta;
        }

        scale += delta;
        angle += 0.01;

        repaint();
    }
}

public class Star extends JFrame {

    public Star() {

        initUI();
    }

    private void initUI() {
        
        setTitle("Star");

        add(new Surface2());

        setSize(420, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                Star st = new Star();
                st.setVisible(true);
            }
        });
    }
}