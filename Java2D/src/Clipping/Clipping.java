package Clipping;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


class Surface extends JPanel 
        implements ActionListener {

    private int pos_x = 8;
    private int pos_y = 8;
    private int radius = 90;

    private Timer timer;
    private Image image;

    private double delta[] = { 3, 3 };

    public Surface() {
        
        loadImage();
        determineAndSetImageSize();
        initTimer();
    }
    
    private void loadImage() {
        
        image = new ImageIcon("images\\mushrooms.jpg").getImage();
    }
    
    private void determineAndSetImageSize() {
        
        int h = image.getHeight(this);
        int w = image.getWidth(this);
        setPreferredSize(new Dimension(w, h));        
    }    

    private void initTimer() {   

        timer = new Timer(35, this);
        timer.start();
    }
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setClip(new Ellipse2D.Double(pos_x, pos_y, radius, radius));

        g2d.drawImage(image,  5, 5, null);        
    }

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        doDrawing(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        int w = getWidth();
        int h = getHeight();

        if (pos_x < 0) {
            
            delta[0] = Math.random() % 4 + 5;
        } else if (pos_x > w - radius) {
            
            delta[0] = -(Math.random() % 4 + 5);
        }

        if (pos_y < 0 ) {
            
            delta[1] = Math.random() % 4 + 5;
        } else if (pos_y > h - radius) {
            
            delta[1] = -(Math.random() % 4 + 5);
        }

        pos_x += delta[0];
        pos_y += delta[1];

        repaint();
    }       
}

public class Clipping extends JFrame {
    
    public Clipping() {
        
        initUI();
    }
    
    private void initUI() {
        
        setTitle("Clipping");

        add(new Surface());

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                Clipping cl = new Clipping();
                cl.setVisible(true);
            }
        });        
    }
}