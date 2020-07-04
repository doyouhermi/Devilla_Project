/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectA;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author HERMI
 */
public class Character {
    
   int moveX , moveCountX , v , moveCountY;
   public static String choosen_char;
   Image mainC;
   
   public Character(){
        moveX = 30;
        v = 330;
        moveCountY = 0;
   }
  
   
   public Rectangle getBounds(){
       return new Rectangle(moveX , v , 140 , 140);
   }
   
   public int getMoveX(){
       return moveX;
   }
   
   public int getMoveCountX(){
       return moveCountX;
   }
   
   public int getMoveCountY(){
       return moveCountY;
   }
   
   public int getY(){
       return v;
   }
   
   public void setImage(Image a){
       mainC = a;
   }
   public Image getImage(){
       return mainC;
   }
   
   public void KeyPressed(KeyEvent e){
       
       int key = e.getKeyCode();
         
       if(key == KeyEvent.VK_SPACE){
           
           moveCountY=1; 
           ImageIcon j = new ImageIcon(getClass().getResource("/"+ choosen_char + "_3.png"));
           mainC = j.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT); 
       }
   }
   
   public void KeyReleased(KeyEvent e){
       int key = e.getKeyCode();
       if(key == KeyEvent.VK_SPACE){
           moveCountY = 0;    
            
       }
   }

   
}
