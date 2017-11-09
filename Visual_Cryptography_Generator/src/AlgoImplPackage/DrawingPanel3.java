/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */
package AlgoImplPackage;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author User
 */
public class DrawingPanel3 extends JPanel {
    
    BufferedImage image;
    ImagePanel imagepanel;
    
    public DrawingPanel3(BufferedImage image, String imagename) {
        setLayout(new GridLayout(2, 1));
           
           JLabel lblFinalImage = new JLabel(imagename, SwingConstants.CENTER);
    this.image = image;    
    imagepanel = new ImagePanel(image);
    
    add(lblFinalImage);
    add(imagepanel);
    
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
       // g.drawImage(image, 0, 0, this);
               
    }
    
    
    
}
