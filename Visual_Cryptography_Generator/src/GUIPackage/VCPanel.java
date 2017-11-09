/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */

/*
 * VC.java
 *
 * Created on 10 Nov, 2012, 11:42:41 PM
 */
package GUIPackage;

import AlgoImplPackage.VCEncryption;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar.Separator;

/**
 *
 * @author User
 */
public class VCPanel extends javax.swing.JPanel implements ActionListener{

    /** Creates new form VC */
    public VCPanel() {
        initComponents();
        displayPanels();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void displayPanels() {
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        
        
        algopanel = new SelectAlgoPanel();
        infopanel = new SelectInfoPanel();
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
       
        JButton btn = new JButton();
        btn.setText("ENCRYPT");
        
        JSeparator jsp = new JSeparator(Separator.HORIZONTAL);
        
        this.add(algopanel,BorderLayout.CENTER);
        this.add(jsp);
        this.add(infopanel,BorderLayout.CENTER);
        this.add(btn);        
         
        btn.addActionListener(this);
    }
    
    private SelectAlgoPanel algopanel;
    private SelectInfoPanel infopanel;
    private String errormsg = "fill in complete details";

    @Override
    public void actionPerformed(ActionEvent e) {
        
        VCEncryption vcencrypt;
        int selalgoindex = algopanel.getAlgoSelection();
        
         String[] datachoice=new String[2];
         datachoice= infopanel.getUserInput();
                  
         if(datachoice==null)
         {
             JOptionPane.showMessageDialog(this, errormsg);
         }
         else
         {
             // sending text for VCEncryption process
             if (datachoice[0].equals("text")) 
             {
                 vcencrypt = new VCEncryption(datachoice[1], selalgoindex);
             } 
             // loading image file from specified user input
             // sending image file for VCEncryption process
             if (datachoice[0].equals("image")) 
             {
                 File imagefile = new File(datachoice[1]);
                 vcencrypt = new VCEncryption(imagefile, selalgoindex);
             }
         }
        
        
    }
}