/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */
package AlgoImplPackage;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ImagePanel extends JPanel {

    BufferedImage image;
    
    
    public ImagePanel(BufferedImage image) {

        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        
        g.drawImage(image, this.getWidth()/2-image.getWidth()/2, this.getHeight()/2-image.getHeight()/2, this);
        
    
    }

    
    
    
}
