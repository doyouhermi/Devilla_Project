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
public class Score_sheet {
    
    
    Score_sheet(int user_no ,String username , int score , String ch){
        this.username = username;
        this.score = score;
        this.user_no = user_no;
        this.ch = ch;
    }
    
    public String getUsername(){
        return username;

    }
    
    
    public int getScore(){
        return score;

    }
    
    
    public String getCh(){
        return ch;

    }
    
    public int getUser_No(){
        return user_no;
    }
    
    
    private int user_no;
    private String ch;
    private String username;
    private int score;
    
    
    
}
