/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.packets;

/**
 *
 * @author alfredo
 */
public class ResponsePacket extends Packet {
    
    private Boolean congratulationsMode=false;
    private Integer totalScore=-1;
    
    
    
    private Boolean gameMode=false;
    private String currentWordView="not set";
    private Integer failedAttemptsCounter=-1;
        
    
    
    private Boolean gameOverMode=false;

    public Boolean isCongratulationsMode() {
        return congratulationsMode;
    }

   

    public Integer getTotalScore() {
        return totalScore;
    }

    public Boolean isGameMode() {
        return gameMode;
    }

    public String getCurrentWordView() {
        return currentWordView;
    }

    public Integer getFailedAttemptsCounter() {
        return failedAttemptsCounter;
    }

    public Boolean isGameOverMode() {
        return gameOverMode;
    }
    
    
    
    public void setCongratulation(String w , Integer score){
        this.currentWordView=w;
        this.totalScore=score;
        this.congratulationsMode=true;
        this.gameOverMode=false;
        this.gameMode=false;       
        
    }
    
    public void setGameMode(String currentView, Integer attempts){
        this.currentWordView=currentView;
        this.failedAttemptsCounter=attempts;
        
        this.congratulationsMode=false;
        this.gameOverMode=false;
        this.gameMode=true;     
    }
    
    
     public void setGameOverMode(){
        
        this.congratulationsMode=false;
        this.gameOverMode=true;
        this.gameMode=false;     
    }
    
    
    
}
