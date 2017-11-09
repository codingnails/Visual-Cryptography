/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */
package AlgoImplPackage;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author User
 */
public class DrawingPanel2 extends JPanel {
    BufferedImage verficationshare, hostimage;
    int verfsharewidth;
    int hostimagewidth;
    ImagePanel imagepanel;
    public DrawingPanel2(BufferedImage verficationshare,BufferedImage hostimage )
    {
     
     this.verficationshare = verficationshare;
     this.hostimage = hostimage;
     
     verfsharewidth = verficationshare.getWidth();
     hostimagewidth = hostimage.getWidth();
     
     
     setLayout(new GridLayout(2, 2, 2, 7));      
     setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
   
    JLabel lblverfshare =new JLabel("VERIFICATION SHARE", SwingConstants.CENTER);
     JLabel lblhostimg =new JLabel("HOST IMAGE", SwingConstants.CENTER);
    
     add(lblverfshare);
     add(lblhostimg); 
     imagepanel = new ImagePanel(verficationshare);
     add(imagepanel);
   imagepanel = new ImagePanel(hostimage);
    add(imagepanel);
     
    }

   
    
}
