package Translation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Surface4 extends JPanel {

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        AffineTransform tx1 = new AffineTransform();
        tx1.translate(50, 90);

        g2d.setTransform(tx1);
        g2d.setColor(Color.green);
        g2d.drawRect(0, 0, 160, 50);

        AffineTransform tx2 = new AffineTransform();
        tx2.translate(50, 90);
        tx2.shear(0, 1);

        g2d.setTransform(tx2);
        g2d.setColor(Color.blue);

        g2d.draw(new Rectangle(0, 0, 80, 50));

        AffineTransform tx3 = new AffineTransform();
        tx3.translate(130, 10);
        tx3.shear(0, 1);

        g2d.setTransform(tx3);
        g2d.setColor(Color.red);
        g2d.drawRect(0, 0, 80, 50);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class Shearing extends JFrame {

    public Shearing() {

        initUI();
    }

    private void initUI() {

        setTitle("Shearing");

        add(new Surface4());

        setSize(330, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                Shearing sh = new Shearing();
                sh.setVisible(true);
            }
        });
    }
}