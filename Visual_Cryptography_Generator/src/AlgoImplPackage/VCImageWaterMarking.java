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
public final class VCImageWaterMarking implements ActionListener{
    private int secretkey;
    private String text=null;
    private File imagefile = null;
    
    private BufferedImage watermark=null, hostimg=null, watermarkoutput; 
    private WaterMarkEmbedding embdwatermark;
    private BufferedImage verficationshare;
    private DrawingPanel2 drawpanel;
    private DrawingPanel3 drawatermarkpanl;
    private WaterMarkExtraction extractwatermark;
    
    public VCImageWaterMarking(int secretkey, String text, File imagefile) {
    this.secretkey = secretkey;
    this.text = text;
    this.imagefile = imagefile;
 
    performImageWatermarkEmbedding(this.secretkey, this.text, this.imagefile);
    }
    
    public void performImageWatermarkEmbedding(int secretkey, String text, File imagefile)
    {
        BufferedImage image=null;
            // creating watermark image
            watermark = new BufferedImage(200, 200, BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g1 = watermark.createGraphics();
            Font font = new Font("serif", Font.BOLD, 25);
            FontMetrics fontmetrix = g1.getFontMetrics(font);
       int textwidth = fontmetrix.stringWidth(text);
       int textheight = fontmetrix.getHeight();
       g1.setFont(font);
       
       g1.drawString(text, watermark.getWidth()/2-textwidth/2, watermark.getHeight()/2);
             
            // loading image from file path
            try {
            image = ImageIO.read(imagefile);
            } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Unable to load file");
        }
            
            // converting loaded image to black and white host image
            hostimg  =  new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g = hostimg.createGraphics();
            g.drawImage(image, 0, 0, null);
            
        
             embdwatermark = new WaterMarkEmbedding(secretkey, watermark, hostimg);
             verficationshare = embdwatermark.createVerificationShare();
             System.out.println("verfication share created");
             drawpanel = new DrawingPanel2(verficationshare, hostimg);
             
             JFrame frame = new JFrame("Sender site: verfication share and host image");
             frame.setLayout(new BorderLayout());
             
             JButton btn = new JButton("Show Watermark Extraction");
             frame.add(drawpanel, BorderLayout.CENTER);
             frame.add(btn, BorderLayout.SOUTH);
             frame.pack();
             frame.setSize(verficationshare.getWidth()*2 +10, verficationshare.getHeight()*2+13);
             frame.setVisible(true);
             frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             
             btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        extractwatermark = new WaterMarkExtraction(secretkey, verficationshare, hostimg);
        watermarkoutput = extractwatermark.extractWatermark();
             
        drawatermarkpanl = new DrawingPanel3(watermarkoutput, "WATERMARK");
        
        JFrame frame = new JFrame("Receiver site: Watermark");
             
             
             frame.setContentPane(drawatermarkpanl);
             frame.setSize(watermark.getWidth(),watermark.getHeight());
             frame.setSize(watermarkoutput.getWidth() +15, hostimg.getHeight()*2+13);
             frame.setVisible(true);
             frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
}
