/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1;

import java.io.Serializable;

/**
 *
 * @author alfredo
 */
public class ResponsePacket implements Serializable{
    
    private Boolean congratulationsMode=false;
    private String word;
    private Integer totalScore;
    
    private Boolean gameMode=false;
    private String currentWordView;
    private Integer failedAttemptsCounter;
    
    private Boolean gameOverMode=false;
    
    public void setCongratulation(String w , Integer score){
        this.word=w;
        this.totalScore=score;
        this.congratulationsMode=true;
        this.gameOverMode=false;
        this.gameMode=false;       
        
    }
    
    public void setGameMode(String currentView, Integer attempts){
         this.word=w;
        this.totalScore=score;
        this.congratulationsMode=true;
        this.gameOverMode=false;
        this.gameMode=false;     
    }
    
    
    
}
