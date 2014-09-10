package Composition;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


class Surface2 extends JPanel implements ActionListener {
        
    private Image sun;
    private Image cloud;
    private Timer timer;    
    private float alpha;    
    
    public Surface2() {
        
        loadImages();
        initTimer();
    }
    
    private void loadImages() {
        
        sun = new ImageIcon("images\\sun.png").getImage();
        cloud = new ImageIcon("images\\cloud.png").getImage();
    }
    
    private void initTimer() {
        
        timer = new Timer(800, this);
        timer.start();
        alpha = 1f;                
    }

    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        BufferedImage buffImg = new BufferedImage(220, 140,
                                    BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbi = buffImg.createGraphics();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                alpha);

        gbi.drawImage(sun, 40, 30, null);
        gbi.setComposite(ac);
        gbi.drawImage(cloud, 0, 0, null);

        g2d.drawImage(buffImg, 20, 20, null);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        alpha -= 0.1;
        
        if (alpha <= 0) {
            
            alpha = 0;
            timer.stop();
            System.out.println("Timer stopped.");
        }
        
        repaint();
    }        
}

public class SunAndCloud extends JFrame {

    public SunAndCloud() {
        
        initUI();
    }
    
    private void initUI() {
        
        setTitle("Sun and Cloud");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new Surface2());

        setSize(300, 210);
        setLocationRelativeTo(null);        
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                SunAndCloud sac = new SunAndCloud();
                sac.setVisible(true);
            }
        });
    }
}
