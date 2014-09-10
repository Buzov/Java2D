package Images;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Surface extends JPanel {

    private Image mshi;

    public Surface() {
        
        loadImage();
        setSurfaceSize();
    }

    private void loadImage() {

        mshi = new ImageIcon("images\\mushrooms.jpg").getImage();
    }
    
    private void setSurfaceSize() {
        
        Dimension d = new Dimension();
        d.width = mshi.getWidth(null);
        d.height = mshi.getHeight(null);
        setPreferredSize(d);        
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(mshi, 1, 1, null);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class DisplayImage extends JFrame {

    public DisplayImage() {

        initUI();
    }

    private void initUI() {

        setTitle("Mushrooms");

        add(new Surface());

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DisplayImage ex = new DisplayImage();
                ex.setVisible(true);
            }
        });
    }
}