/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */
package AlgoImplPackage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author User
 */
public class WaterMarkEmbedding {

    public static final int WHITE = Color.WHITE.getRGB();
    public static final int BLACK = Color.BLACK.getRGB();
    BufferedImage host_img, watermark;
    private int secret_key;
    
    
    public WaterMarkEmbedding( int secret_key,  BufferedImage watermark, BufferedImage host_img) {
        System.out.println("initialized");
        this.host_img  =  new BufferedImage(host_img.getWidth(), host_img.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g = this.host_img.createGraphics();
        g.drawImage(host_img, 0, 0, null);
        this.watermark = watermark;
        this.secret_key = secret_key;
    }
    //hostimage of size x*y and watermark size p*q
    
    
    
    
    public BufferedImage createVerificationShare()
    {
        int hostimg_x = host_img.getWidth();
        int hostimg_y = host_img.getHeight();
        int xy = hostimg_x*hostimg_y;
    
        int watermark_p = watermark.getWidth();
        int watermark_q = watermark.getHeight();
        
        BufferedImage verfication_share = new BufferedImage(watermark_p*2, watermark_q, BufferedImage.TYPE_BYTE_BINARY);
        
        Random random = new Random(secret_key);
        
        for(int p = 0; p<watermark_p; p++)
            for(int q = 0; q<watermark_q; q++)
            {
                int i = Integer.lowestOneBit(random.nextInt(xy));
                int pixel_value = watermark.getRGB(p, q);
                if(pixel_value==BLACK)
                {
                    if(i==1)
                    {
                       verfication_share.setRGB(2*p, q, WHITE );
                       verfication_share.setRGB(2*p+1, q, BLACK);
                    }
                    else
                    {
                        verfication_share.setRGB(2*p, q, BLACK );
                       verfication_share.setRGB(2*p+1, q, WHITE);
                    }
                    
                }
                else
                {
                    if(i==1)
                    {
                       verfication_share.setRGB(2*p, q, BLACK );
                       verfication_share.setRGB(2*p+1, q, WHITE);
                    }
                    else
                    {
                        verfication_share.setRGB(2*p, q, WHITE);
                       verfication_share.setRGB(2*p+1, q, BLACK);
                    }
                }
                  
                    
            }
        
       
        return verfication_share;
    }
    
    
}
