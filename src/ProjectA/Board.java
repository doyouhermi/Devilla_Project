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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Board extends JPanel implements ActionListener , KeyListener , Runnable{

Character ch;
Obstacles obs1;
Obstacles obs2;
Image img;
Image img_bg;
Image img_bg2;
Timer time;
Thread animator;
JLabel meter_label;
ImageIcon j;

int meter = 0;
int gif = 0;
int count = 0;
int mods = 0;
int pos=0;
int pos2 = 0;
JLabel a;
JLabel b;

boolean lost = false;
 
    public Board(){
        
        
        obs1 = new Obstacles(1200 , 245 , "getClass().getResource(\"/cone.png\"))");
        obs2 = new Obstacles(2200 , 245 , "getClass().getResource(\"/tree.png\"))");
        ch = new Character();
        meter_label = new JLabel();
           
        ImageIcon bg = new ImageIcon(getClass().getResource("/back.jpg"));
        ImageIcon bg2 = new ImageIcon(getClass().getResource("/back.jpg"));
//        
        img_bg = bg.getImage();
        img_bg2 = bg.getImage();
//        
        
        a = new JLabel();
        a.setIcon(bg);
        b = new JLabel();
        b.setIcon(bg2);
        
        
        meter_label.setFont(new java.awt.Font("Arial Black", 0, 24));
        meter_label.setBounds(350, 30, 300, 40);
        meter_label.setHorizontalAlignment(SwingConstants.CENTER);
        meter_label.setVerticalAlignment(SwingConstants.CENTER);
        
        setLayout(null);
        add(meter_label);
        add(b);
        add(a);
        setFocusable(true);
        addKeyListener(this);
        
        j = new ImageIcon("D:\\Netbeans\\Projects\\ProjectZ\\src\\1.png");
        img = j.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
       
        time = new Timer(3 , this);
        time.start();
        
    }

    int endtime = 0;
    @Override
    public void actionPerformed(ActionEvent e) {   
        
        
        playerAnimation();
        
//        if(count % 100 == 0)
//            pbar.setValue(mods++);
//        
//        if(mods == 101){
//            
//            MainFrame f = new MainFrame();
//            f.setVisible(true); 
//            time.stop();
//            MainFrame.a.closeFrame();
//        }

// movement of background start
         pos--;
         
         if(count % 5 == 0){
             meter++;
         }
             
         
         meter_label.setText( meter + " METERS" );
         
         if(pos <= -15)
            pos2--;
         if(pos2 <= -14)
             pos2--;
         
        if(pos == -15)
            pos2 = 980;
        
         if(pos2 == -15)
             pos = 980;
// movement of background end




        if(collision() || endtime == 1){
            
            JOptionPane.showMessageDialog(null , "Game is Over");
            MainFrame f = new MainFrame();
            f.setVisible(true); 
            time.stop();
            MainFrame.a.closeFrame();
            
            saveData(MainFrame.username , meter);
            
        }else{
            obs1.move();
            obs2.move();
            count++;
            gif++;
        }
        if(obs1.getX() < -10){
            obs1.setPosX(2200 );
            //obs1.setSpeed(obs1.getSpeed()+1);
        }
        if(obs2.getX() < -10){
            obs2.setPosX(2200);
           // obs2.setSpeed(obs2.getSpeed()+1);
        }
        repaint();
    }
    
    public boolean collision(){
        
        Rectangle mainRec = ch.getBounds();
        Rectangle obj = obs1.getBounds();
        Rectangle obj2 = obs2.getBounds();
        
        if(mainRec.intersects(obj)){
            
            System.out.println(obs1.getX() + " == " + ch.getMoveX());
            System.out.println(obs1.getY() + " == " + ch.getY());
            return true;
        }return mainRec.intersects(obj2);
    }
    
    boolean flag = false;
    
    
    
@Override
    public void paint(Graphics g){
        
       // System.out.println(ch.moveCountY + " == 1" + flag + " == false");
        if(ch.moveCountY == 1 && flag == false){ 
             flag = true;
             animator = new Thread(this);
             animator.start();
        }
            
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
           
       if(ch.v == 330){
           ch.mainC = img;
       }
     
        if(pos2 < -15){
           // g2d.drawImage(img_bg2 , pos2, 0 , null);
            b.setBounds(pos2, 0, 995, 610);
        
        }
        
        if(pos <= -15){
            b.setBounds(pos2, 0, 995, 610);
            //g2d.drawImage(img_bg2 , pos2, 0 , null);
            
        }
           
        
        a.setBounds(pos, 0, 995, 610);
        g2d.drawImage(ch.getImage() , ch.getMoveX() , ch.v , null);
        g2d.drawImage(obs1.getImage() , obs1.getX() , obs1.getY() , null);
        g2d.drawImage(obs2.pic2 , obs2.getX() , obs2.getY() , null);
                
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
         ch.KeyPressed(e);
    } 

    @Override
    public void keyReleased(KeyEvent e) {
         ch.KeyReleased(e);
         
    }

    @Override
    public void run() {
        
          
        long beforeTime , timeDifference , timeSleep;
        
        beforeTime = System.nanoTime();
        while(done == false){
            
            cycle(); 
            timeDifference = System.nanoTime() - beforeTime;
            timeSleep = 3 - timeDifference;
            if(timeSleep < 0)
                timeSleep = 2;
            
            try{
                
                
               Thread.sleep(timeSleep);
            }catch(Exception e){
            }
            
            beforeTime = System.nanoTime();
           
        }
        
         max_h = false;
         done = false;
         flag = false;
               
            
    }
    
    boolean max_h = false; // peak of the height 
    boolean done = false; // if the jump is done
    
    public void cycle(){
        
        
        if(collision()){
            endtime = 1;
            animator.stop();
        }else{
            if(max_h == false)
                ch.v--;
            if(ch.v == 150)
                max_h = true;

            if(max_h == true & ch.v <= 330){
                ch.v++;
                if(ch.v == 330){
                    done = true;
                   
                }
            }
        }
    }// movement of the y of player
    
    
    public void playerAnimation(){
         
        switch (gif) {
            case 5:
                j= new ImageIcon(getClass().getResource("/" + Character.choosen_char + "_1.png"));
                img = j.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
                ;
                break;
            case 15:
                j =  new ImageIcon(getClass().getResource("/"+ Character.choosen_char + "_2.png"));
                img = j.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
                break;
            case 20:
                j =  new ImageIcon(getClass().getResource("/"+ Character.choosen_char + "_3.png"));
                img = j.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);

                break;
            case 35:
                j =  new ImageIcon(getClass().getResource("/" + Character.choosen_char + "_4.png"));
                img = j.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);

                break;
            case 45:
                j =  new ImageIcon(getClass().getResource("/" + Character.choosen_char + "_5.png"));
                img = j.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);

                break;
            case 55:
                j =  new ImageIcon(getClass().getResource("/" + Character.choosen_char + "_6.png"));
                img = j.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);

                break;
            case 65:
    //            j= new ImageIcon("D:\\Netbeans\\Projects\\ProjectZ\\src\\shi_1.png");
    //            img = j.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
    //            
                gif = 0;
                break;
            default:
                break;
        }

    }
    
    
    public void saveData(String user , int score){
       
        try{  
            //step1 load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step2 create  the connection object  
            Connection con=DriverManager.getConnection(  
            "jdbc:oracle:thin:@localhost:1521:xe","Hermi","root");  

            //step3 create the statement object  
            Statement stmt=con.createStatement();  

            //step4 execute query  
           
            stmt.executeUpdate("INSERT INTO SCORE_SHEET VALUES (id_seq.nextval , '" +user+"' , " + score +" , '" + Character.choosen_char +"')");

            //step5 close the connection object  
            con.close();  

        }catch(Exception e){ System.out.println(e);}  

    }

}
