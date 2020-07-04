/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectA;

/**
 *
 * @author HERMI
 */

import javax.swing.*;

public class Frame{
    
    
    public  void showFrame(){
        
        
        frame.setTitle("Love is War");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new java.awt.Dimension(995, 610));
        frame.setMinimumSize(new java.awt.Dimension(995, 610));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(new Board());
        frame.setVisible(true);
        
    }
    
    
    public  void closeFrame(){
        frame.setVisible(false);

    }
    
    
    
public  JFrame frame = new JFrame();
     
    
}
