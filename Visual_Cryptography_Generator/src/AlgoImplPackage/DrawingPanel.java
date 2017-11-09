/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */
package AlgoImplPackage;


import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 *
 * @author User
 */
class DrawingPanel extends JPanel{
       BufferedImage[] img; 
       ImagePanel imagepanel;
       Dimension minsize;
      
      

  DrawingPanel (BufferedImage[] img) 
  { 
      this.img = img;
      setLayout(new GridLayout(2, 2, 2, 3));
      setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    
      
   
    JLabel lblshare1 =new JLabel("SHARE 1", SwingConstants.CENTER);
     JLabel lblshare2 =new JLabel("SHARE 2", SwingConstants.CENTER);
    imagepanel = new ImagePanel(img[0]);
    
 
   
   add(lblshare1);
   add(lblshare2);
   add(imagepanel);
   
   imagepanel = new ImagePanel(img[1]);
   add(imagepanel);
   
   minsize = new Dimension(imagepanel.getWidth()*2, imagepanel.getHeight()*2);
   
  }

    @Override
    public Dimension getPreferredSize() {
        return minsize;
    }
  
  

   
  
 
}
