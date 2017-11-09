/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */
package AlgoImplPackage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author User
 */
public class WaterMarkExtraction {
    
    public static final int WHITE = Color.WHITE.getRGB();
    public static final int BLACK = Color.BLACK.getRGB();
    private BufferedImage marked_img, verf_share;
    int secret_key;
    
    
    public WaterMarkExtraction(int secret_key, BufferedImage verf_share, BufferedImage marked_img) {
        this.marked_img = marked_img;
        this.verf_share = verf_share;
        this.secret_key = secret_key;
    }
    
    
    
    public BufferedImage extractWatermark()
    {
        int marked_x = marked_img.getWidth();
        int marked_y = marked_img.getHeight();
        int xy = marked_x*marked_y;
    
        // watermark of size p*q
        int watermark_p = verf_share.getWidth()/2;
        int watermark_q = verf_share.getHeight();
        
        BufferedImage watermark = new BufferedImage(watermark_p, watermark_q, BufferedImage.TYPE_BYTE_BINARY);
        BufferedImage master_share = new BufferedImage(verf_share.getWidth(), verf_share.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        
        Random random = new Random(secret_key);
        
        for(int p = 0; p<watermark_p; p++)
            for(int q = 0; q<watermark_q; q++)
            {
                int i = Integer.lowestOneBit(random.nextInt(xy));
               
                    if(i==1)
                    {
                        master_share.setRGB(2*p, q, WHITE );
                        master_share.setRGB(2*p+1, q, BLACK);
                    }
                    else
                    {
                        master_share.setRGB(2*p, q, BLACK );
                        master_share.setRGB(2*p+1, q, WHITE);
                    }
            }
        
        for(int p = 0; p<verf_share.getWidth()-1; p+=2)
            for(int q = 0; q<verf_share.getHeight(); q++)
            {
                if((verf_share.getRGB(p, q)==master_share.getRGB(p, q)) && (verf_share.getRGB(p+1, q)==master_share.getRGB(p+1, q)))
                    watermark.setRGB(p/2, q, WHITE);
                else
                    watermark.setRGB(p/2, q, BLACK);
            }
        
       
        return watermark;
    }
    
    
}


