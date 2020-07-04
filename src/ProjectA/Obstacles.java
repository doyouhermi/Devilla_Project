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


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Obstacles{
    
    Image pic;
    Image pic2;
    private int posX;
    int posY;
    private int enemyspeed = 3;
    
    
    boolean isAlive = true;
    
    public Obstacles(int startX , int startY , String location){
        posX = startX;
        posY = startY;
        
        ImageIcon l = new ImageIcon(getClass().getResource("/cone.png"));        
        ImageIcon k = new ImageIcon(getClass().getResource("/tree.png"));

        pic = l.getImage().getScaledInstance(200, 300, Image.SCALE_DEFAULT);
        pic2 = k.getImage().getScaledInstance(200, 300, Image.SCALE_DEFAULT);
    }
    
    
    public void setSpeed(int enemyspeed){
        this.enemyspeed = enemyspeed;
    }
    
    public int getSpeed(){
        return enemyspeed;
    }
    
    public void setPosX(int posX){
        this.posX = posX;
    }
    
    public int getX(){
        return posX;
    }
    
    public int getY(){
        return posY;
    }
    
    public boolean isAlive(){
        return isAlive;
    }
    
    public Image getImage(){
        return pic;
    }
    
    public void move(){
        posX = posX - enemyspeed;
    }
    
    public Rectangle getBounds(){
        return new Rectangle (posX+80, posY+150, 30 , 50);
    }
}

