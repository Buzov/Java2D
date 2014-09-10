package Images;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Surface4 extends JPanel {

    private BufferedImage mshi;
    private BufferedImage databuf;

    public Surface4() {

        loadImage();
        createBlurredImage();
        setSurfaceSize();
    }

    private void loadImage() {
        
        try {
            
            mshi = ImageIO.read(new File("images\\mushrooms.jpg"));
        } catch (IOException ex) {
            
            Logger.getLogger(Surface4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createBlurredImage() {
        
        databuf = new BufferedImage(mshi.getWidth(null),
                mshi.getHeight(null),
                BufferedImage.TYPE_INT_BGR);

        Graphics g = databuf.getGraphics();
        g.drawImage(mshi, 455, 255, null);

        float[] blurKernel = {
            1 / 9f, 1 / 9f, 1 / 9f,
            1 / 9f, 1 / 9f, 1 / 9f,
            1 / 9f, 1 / 9f, 1 / 9f
        };

        BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
        mshi = blur.filter(mshi, new BufferedImage(mshi.getWidth(),
                mshi.getHeight(), mshi.getType()));
        g.dispose();        
    }
    
    private void setSurfaceSize() {
        
        Dimension d = new Dimension();
        d.width = mshi.getWidth(null);
        d.height = mshi.getHeight(null);
        setPreferredSize(d);        
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(mshi, null, 3, 3);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class BlurredImage extends JFrame {

    public BlurredImage() {

        setTitle("Blurred image");

        add(new Surface4());

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                BlurredImage bi = new BlurredImage();
                bi.setVisible(true);
            }
        });
    }
}
