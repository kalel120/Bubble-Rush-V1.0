/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packageMM;

import java.io.*;
import java.awt.*;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

// Extends JPanel, so as to override the paintComponent() for custom rendering codes. 
 /*
 * @author Md. G R Tushar
 */


public class BouncingBallSimple extends JPanel implements KeyListener{
    
       char f=' ';
      private int i=0;
    
     public void keyPressed(KeyEvent e)
     {
         f=e.getKeyChar();  
          if(f!=c)
             negcount++;
         
     }
     public void keyReleased(KeyEvent e)
     {
        
     }
    
    public void keyTyped(KeyEvent e)
    {
    
    }
     // Container box's width and height
   private static final int BOX_WIDTH = 1350;
   private static final int BOX_HEIGHT = 700;
 
  Random r = new Random();
   
   int count=0;
    int negcount=0;
   int flag=0;
   int l=0;
  
   char c='Q';
   // Ball's properties
   private int ballRadius = 20; // Ball's radius
   private int ballX = ballRadius + 50; // Ball's center (x, y)
  
   private int ballY = ballRadius + 640; 
   private int ballSpeedX = 3;   // Ball's speed for x and y
   private int ballSpeedY = 3;
  
   private static final int UPDATE_RATE = 30; // Number of refresh per second
  
   /** Constructor to create the UI components and initialize game objects. */
    /**
     * Creates new form BouncingBallSimple
     */
    public BouncingBallSimple(int n) {
      
          this.addKeyListener(this);
        this.setFocusable(true);
        ballX=ballX+n;
        
         this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
         
      
  
      // Start the ball bouncing (in its own thread)
      Thread gameThread = new Thread() {
         public void run() {
            while (true) { 
                 // Execute one update step
               // Calculate the ball's new position
               ballX += ballSpeedX;
               ballY += ballSpeedY;
               
               if (ballX - ballRadius < 0) {
                  ballSpeedX = -ballSpeedX; // Reflect along normal
                  ballX = ballRadius; // Re-position the ball at the edge
               } else if (ballX + ballRadius > BOX_WIDTH) {
                  ballSpeedX = -ballSpeedX;
                  ballX = BOX_WIDTH - ballRadius;
               }
               // May cross both x and y bounds
               if (ballY - ballRadius < 0) {
                  ballSpeedY = -ballSpeedY;
                  ballY = ballRadius;
               } else if (ballY + ballRadius > BOX_HEIGHT) {
                  ballSpeedY = -ballSpeedY;
                  ballY = BOX_HEIGHT - ballRadius;
               }
               // Refresh the display
               repaint(); // Callback paintComponent()
               // Delay for timing control and give other threads a chance
               try {
                  Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
               } 
               catch (InterruptedException ex) { }
               
               
            }
         }
     };
      gameThread.start();
      
   }

    
   @Override
   public void paintComponent(Graphics g) {
       
      
       
            super.paintComponent(g);    // Paint background
  
      // Draw the box
      g.setColor(Color.ORANGE);
      g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
      
      //count
      g.setColor(Color.WHITE);
      g.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
      StringBuilder sb = new StringBuilder();
      Formatter formatter = new Formatter(sb);
      formatter.format("count=%d",count);
      g.drawString(sb.toString(), 20, 30);
      
      //negcount
      g.setColor(Color.WHITE);
      g.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 16));
      StringBuilder sb1 = new StringBuilder();
      formatter = new Formatter(sb1);
      formatter.format("negcount=%d",negcount);
      g.drawString(sb1.toString(), 20, 45);
  
      // Draw the ball
     
      g.setColor(Color.BLUE);
      g.fillOval((int) (ballX - ballRadius), (int) (ballY - ballRadius),
            (int)(2 * ballRadius), (int)(2 * ballRadius));
      
    
     
 
      if(f==c)
      {
          g.setColor(Color.BLUE);
          count++;
          c = (char) (r.nextInt(26) + 'A');
          i=0;
         
      }
      
      else
      {    
          g.setColor(Color.red);
          i++;
      }  
      if(i>100)
      {
           c = (char) (r.nextInt(26) + 'A');
           negcount++;
           i=0;
      }
      g.drawString(String.valueOf(c), ballX-4, ballY+4);
       try {
                  Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
               } 
               catch (InterruptedException ex) { }
       
}
      
   
   
    
   
  
   /** main program (entry point) */
    public static void main(String[] args) {
      // Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
             BouncingBallSimple b=new BouncingBallSimple(100);
             BouncingBallSimple c=new BouncingBallSimple(200);
            // Set up main window (using Swing's Jframe)
            JFrame frame = new JFrame("A Bouncing Ball");
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            frame.setContentPane(b);
            frame.setContentPane(c);
           
            //frame.pack();
            
            frame.setSize(1365, 730);
            frame.setVisible(true);
            frame.addKeyListener(null);
         }
      });
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

   
  
  
    
}

