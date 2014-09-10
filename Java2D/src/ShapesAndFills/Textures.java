package ShapesAndFills;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Surface5 extends JPanel {

    private BufferedImage slate;
    private BufferedImage java;
    private BufferedImage pane;
    private TexturePaint slatetp;
    private TexturePaint javatp;
    private TexturePaint panetp;

    public Surface5() {

        loadImages();
    }

    private void loadImages() {

        try {

            slate = ImageIO.read(new File("textures\\slate.png"));
            java = ImageIO.read(new File("textures\\java.png"));
            pane = ImageIO.read(new File("textures\\pane.png"));

        } catch (IOException ex) {

            Logger.getLogger(Surface5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        slatetp = new TexturePaint(slate, new Rectangle(0, 0, 90, 60));
        javatp = new TexturePaint(java, new Rectangle(0, 0, 90, 60));
        panetp = new TexturePaint(pane, new Rectangle(0, 0, 90, 60));

        g2d.setPaint(slatetp);
        g2d.fillRect(10, 15, 90, 60);

        g2d.setPaint(javatp);
        g2d.fillRect(130, 15, 90, 60);

        g2d.setPaint(panetp);
        g2d.fillRect(250, 15, 90, 60);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class Textures extends JFrame {

    public Textures() {

        initUI();
    }
    
    private void initUI() {
        
        setTitle("Textures");

        add(new Surface5());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 120);
        setLocationRelativeTo(null);        
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {

                Textures tx = new Textures();
                tx.setVisible(true);
            }
        });
    }
}
