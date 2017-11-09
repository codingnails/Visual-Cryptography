/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */
package AlgoImplPackage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class VCEncryption implements ActionListener{
    
    public static final int a2_OUT_OF_2_2SUBPIXELS = 1;
    public static final int a2_OUT_OF_2_4SUBPIXELS = 2;
    public static final int RANDOM_GRID = 3;
    
    private File imagefile=null;
    private String text=null;
    private int algochoice;
    private BufferedImage image=null;
    
    Encryption encrypt;
    Decryption decrypt;
    BufferedImage[] shares=null;
    BufferedImage finalimg = null;
    DrawingPanel drawsharepanel;
    DrawingPanel3 drawfinalpanel;
    
    // VCEncryption class constructor for text
    // creating image file from text
    public VCEncryption(String text, int algochoice)
    {
       this.text=text;
       this.algochoice=algochoice;
       image = new BufferedImage(200, 200, BufferedImage.TYPE_BYTE_BINARY);
       Graphics2D g1 = image.createGraphics();
       
          
       Font font = new Font("serif", Font.BOLD, 25);
       FontMetrics fontmetrix = g1.getFontMetrics(font);
       int textwidth = fontmetrix.stringWidth(text);
       int textheight = fontmetrix.getHeight();
       g1.setFont(font);
       
       g1.drawString(text, image.getWidth()/2-textwidth/2, image.getHeight()/2);
       performEncryption();
    }
    
    // VCEncryption class constructor for image file
    //converting image into black and white image 
    public VCEncryption(File file, int algochoice)
    {
       this.imagefile=file;
       this.algochoice=algochoice;
       BufferedImage colorimage=null;
       try {
            colorimage = ImageIO.read(imagefile);
        } catch (IOException ex) {
            
            JOptionPane.showMessageDialog(null, "unable to load image");
        }
       image = new BufferedImage(colorimage.getWidth(), colorimage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
       Graphics2D g = image.createGraphics();
       g.drawImage(colorimage,0,0,null);
       performEncryption();
    }
    
    private void performEncryption()
    {
        encrypt = new Encryption(image);
        
        // calling function based on algo selection
        if(algochoice==a2_OUT_OF_2_2SUBPIXELS)
            shares = encrypt.generateShares2OutOf2_2Subpixels();
        if(algochoice==a2_OUT_OF_2_4SUBPIXELS)
            shares = encrypt.generateShares2OutOf2_4Subpixels();
        if(algochoice==RANDOM_GRID)
            shares = encrypt.generateSharesRandomGrid();
        
        drawsharepanel = new DrawingPanel(shares);
        
        // placing panel on frame
        JFrame frame = new JFrame("Image Shares");
        JButton decryptbtn = new JButton("Merge Shares");
        
        frame.setLayout(new BorderLayout());
        frame.add(drawsharepanel, BorderLayout.CENTER);
        frame.add(decryptbtn, BorderLayout.SOUTH);
        frame.pack();
        frame.setSize(shares[0].getWidth()*2 + 8, shares[0].getHeight()*2+13);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
        
        
        decryptbtn.addActionListener(this);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        decrypt = new Decryption(shares);
        finalimg = decrypt.mergeShares();
        drawfinalpanel = new DrawingPanel3(finalimg, "OVERLAPPED SHARES: ORIGINAL IMAGE");
        
        JFrame frame = new JFrame("Overlapped Shares");
        frame.setContentPane(drawfinalpanel);
        frame.setSize(finalimg.getWidth() + 15, finalimg.getHeight()*2+13);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    
}
