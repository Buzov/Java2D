package Images;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Surface2 extends JPanel {

    private Image mshi;
    private BufferedImage bufimg;
    private Dimension d;

    public Surface2() {

        loadImage();
        determineAndSetSize();
        createGrayImage();
    }

    private void determineAndSetSize() {

        d = new Dimension();
        d.width = mshi.getWidth(null);
        d.height = mshi.getHeight(null);
        setPreferredSize(d);
    }
    
    private void createGrayImage() {
        
        bufimg = new BufferedImage(d.width, d.height, 
                BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D g2d = bufimg.createGraphics();
        g2d.drawImage(mshi, 1, 1, null);
        g2d.dispose();        
    }

    private void loadImage() {

        mshi = new ImageIcon("images\\mushrooms.jpg").getImage();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bufimg, null, 2, 2);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class GrayScaleImage extends JFrame {

    public GrayScaleImage() {
        
        initUI();
    }

    public final void initUI() {

        Surface2 dpnl = new Surface2();
        add(dpnl);

        pack();
        setTitle("GrayScale Image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GrayScaleImage ex = new GrayScaleImage();
                ex.setVisible(true);
            }
        });
    }
}
