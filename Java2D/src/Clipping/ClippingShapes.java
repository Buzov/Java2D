package Clipping;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


class Surface2 extends JPanel
        implements ActionListener {

    private Timer timer;
    private double rotate = 1;
    private int pos_x = 8;
    private int pos_y = 8;
    private int radius = 60;
    private double delta[] = {1, 1};

    public Surface2() {

        initTimer();
    }

    private void initTimer() {

        timer = new Timer(10, this);
        timer.start();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        int w = getWidth();
        int h = getHeight();

        Rectangle rect1 = new Rectangle(0, 0, 200, 80);

        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(rotate), w / 2, h / 2);
        tx.translate(w / 2 - 100, h / 2 - 40);

        Ellipse2D circle = new Ellipse2D.Double(pos_x, pos_y, radius, radius);

        step();

        GeneralPath path = new GeneralPath();
        path.append(tx.createTransformedShape(rect1), false);

        g2d.setColor(new Color(110, 110, 110));
        g2d.clip(circle);
        g2d.clip(path);

        g2d.fill(circle);

        g2d.setClip(new Rectangle(0, 0, w, h));

        g2d.draw(circle);
        g2d.draw(path);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    public void step() {

        int w = getWidth();
        int h = getHeight();

        if (pos_x < 0) {

            delta[0] = 1;
        } else if (pos_x > w - radius) {

            delta[0] = -1;
        }

        if (pos_y < 0) {

            delta[1] = 1;
        } else if (pos_y > h - radius) {

            delta[1] = -1;
        }

        pos_x += delta[0];
        pos_y += delta[1];
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        rotate += 1;
        repaint();
    }    
}

public class ClippingShapes extends JFrame {

    public ClippingShapes() {

        initUI();
    }

    private void initUI() {

        setTitle("Clipping shapes");

        add(new Surface2());

        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                ClippingShapes cs = new ClippingShapes();
                cs.setVisible(true);
            }
        });      
    }
}