/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */
package AlgoImplPackage;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author User
 */
public class Decryption {

    private BufferedImage share1, share2, finalimage=null;
    public Decryption(BufferedImage share[]) {
      share1 = share[0];
      share2 = share[1];
      finalimage = new BufferedImage(share1.getWidth(), share1.getHeight(), BufferedImage.TRANSLUCENT);
      
    }

    public BufferedImage mergeShares()
    { 
        Graphics2D g = finalimage.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.drawImage(share1, 0, 0, null);
        g.drawImage(share2, 0, 0, null);
       
       return finalimage;
    }
    
}
