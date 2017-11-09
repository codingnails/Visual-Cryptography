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
public class Encryption {
  private BufferedImage img;
  public static final int NO_OF_SHARES = 2;
  public static final int WHITE = Color.WHITE.getRGB();
  public static final int BLACK = Color.BLACK.getRGB();
  final int NO_OF_SUBPIXELS = 2;
  int imagepixel;
  int imgwidth, imgheight;
  private BufferedImage[] share=null;
  private Random random;
  
  // constructor initializing image for generation of shares
  public Encryption(BufferedImage img)
  {
    this.img = img;
    imgwidth = img.getWidth();
    imgheight = img.getHeight();
  }
  
  // generating shares of input image
  public BufferedImage[] generateShares2OutOf2_2Subpixels()
  {
      share = new BufferedImage[NO_OF_SHARES];
        
      for(int i=0; i<NO_OF_SHARES; i++)
      {
      share[i]=new BufferedImage(imgwidth*2, imgheight, BufferedImage.TYPE_BYTE_BINARY);
      }
      random = new Random();
      // creating shares using input image
      for(int x=0; x<imgwidth; x++)
      {
          for(int y=0; y<imgheight; y++)
          {
              imagepixel = img.getRGB(x,y);
              
              // selects random value of index 
              int index = random.nextInt(NO_OF_SHARES);
              
              if(imagepixel == WHITE)
              {
                  for(int i=0; i<share.length; i++)
                  if(index==0)
                  {
                    share[i].setRGB(2*x, y, WHITE);
                    share[i].setRGB(2*x+1, y, BLACK);
                  }
                  else
                  {
                    share[i].setRGB(2*x, y, BLACK);
                    share[i].setRGB(2*x+1, y, WHITE);
                  }
              }
              else
              {
                  for(int i=0; i<share.length; i++)
                  if(((index+i)%share.length)==0)
                  {
                    share[i].setRGB(2*x, y, WHITE);
                    share[i].setRGB(2*x+1, y, BLACK);
                  }
                  else
                  {
                    share[i].setRGB(2*x, y, BLACK);
                    share[i].setRGB(2*x+1, y, WHITE);
                  }
              }
              
              
          }
      }
      
      return share;
  }
  
  public BufferedImage[] generateShares2OutOf2_4Subpixels()
  {
      share=new BufferedImage[NO_OF_SHARES];
      for(int i=0; i<NO_OF_SHARES; i++)
      {
      share[i]=new BufferedImage(imgwidth*2, imgheight*2, BufferedImage.TYPE_BYTE_BINARY);
      }
      
      random = new Random();
      int randno;
      
      for(int x=0; x<imgwidth; x++)
          for(int y=0; y<imgheight; y++)
          {
              randno = random.nextInt(NO_OF_SHARES);
              imagepixel = img.getRGB(x, y);
              
              if(imagepixel == WHITE)
              {
                  for(int i=0; i<NO_OF_SHARES; i++)
                  {
                     if(randno==0)
                     {
                         share[i].setRGB(2*x, 2*y, WHITE);
                         share[i].setRGB(2*x, 2*y+1, BLACK);
                         share[i].setRGB(2*x+1, 2*y, BLACK);
                         share[i].setRGB(2*x+1, 2*y+1, BLACK);
                     }
                     else
                     {
                         share[i].setRGB(2*x, 2*y, BLACK);
                         share[i].setRGB(2*x, 2*y+1, WHITE);
                         share[i].setRGB(2*x+1, 2*y, WHITE);
                         share[i].setRGB(2*x+1, 2*y+1, BLACK);
                     }
                  }
              }
              else
              {
                  for(int i=0; i<NO_OF_SHARES; i++)
                  {
                     if((randno+i)%NO_OF_SHARES==0)
                     {
                         share[i].setRGB(2*x, 2*y, WHITE);
                         share[i].setRGB(2*x, 2*y+1, BLACK);
                         share[i].setRGB(2*x+1, 2*y, BLACK);
                         share[i].setRGB(2*x+1, 2*y+1, BLACK);
                     }
                     else
                     {
                         share[i].setRGB(2*x, 2*y, BLACK);
                         share[i].setRGB(2*x, 2*y+1, WHITE);
                         share[i].setRGB(2*x+1, 2*y, WHITE);
                         share[i].setRGB(2*x+1, 2*y+1, BLACK);
                     }
                  }
              }
              
          }
      return share;
  }
  
  public BufferedImage[] generateSharesRandomGrid()
  {
      share = new BufferedImage[NO_OF_SHARES];
              
      for(int i=0; i<NO_OF_SHARES; i++)
      {
      share[i]=new BufferedImage(imgwidth, imgheight, BufferedImage.TYPE_BYTE_BINARY);
      }
      
      random = new Random();
      int randno;
      for(int x =0; x<imgwidth; x++)
          for(int y=0; y<imgheight; y++)
          {
              randno = random.nextInt(2);
              if(randno==0)
              {
                  share[0].setRGB(x, y, WHITE);
              }
              else
              {
                  share[0].setRGB(x, y, BLACK);
              }
              
              imagepixel = img.getRGB(x, y);
              
              if(imagepixel==WHITE)
              {
                  share[1].setRGB(x, y, share[0].getRGB(x, y));
              }
              else
              {
                  if(share[0].getRGB(x, y)==WHITE)
                      share[1].setRGB(x, y, BLACK);
                  else
                      share[1].setRGB(x, y, WHITE);
                  
              }
          }
      
      return share;
  }
  
}

