package Effects;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


class Surface3 extends JPanel 
       implements ActionListener {

   private Timer timer;
   private int x = 1;
   private float alpha = 1;

   public Surface3() {
       
       initTimer();
   }
   
   private void initTimer() {
       
       timer = new Timer(8, this);
       timer.setInitialDelay(190);
       timer.start();               
   }
   
   private void doDrawing(Graphics g) {
       
       Graphics2D g2d = (Graphics2D) g;
       
       RenderingHints rh =
           new RenderingHints(RenderingHints.KEY_ANTIALIASING,
           RenderingHints.VALUE_ANTIALIAS_ON);

       rh.put(RenderingHints.KEY_RENDERING,
              RenderingHints.VALUE_RENDER_QUALITY);

       g2d.setRenderingHints(rh);

       Font font = new Font("Dialog", Font.PLAIN, x);
       g2d.setFont(font);

       FontMetrics fm = g2d.getFontMetrics();
       String s = "ZetCode";
       Dimension size = getSize();

       int w = (int) size.getWidth();
       int h = (int) size.getHeight();

       int stringWidth = fm.stringWidth(s);

       g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
            alpha));

       g2d.drawString(s, (w - stringWidth) / 2, h / 2);        
   }

   @Override
   public void paintComponent(Graphics g) {
       
       super.paintComponent(g);        
       doDrawing(g);
   }   

   @Override
   public void actionPerformed(ActionEvent e) {
       
       x += 1;

       if (x > 40)
           alpha -= 0.01;

       if (alpha <= 0.01)
           timer.stop();
       
       repaint();
   }        
}

public class Puff extends JFrame {    
   
   public Puff() {
       
       initUI();
   }
   
   private void initUI() {
       
       setTitle("Puff");

       add(new Surface3());

       setSize(400, 300);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);        
 
   }

   public static void main(String[] args) {
       
       SwingUtilities.invokeLater(new Runnable() {

           @Override
           public void run() {

               Puff pf = new Puff();
               pf.setVisible(true);
           }
       });      
   }
}
