/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.packets;

import java.io.Serializable;

/**
 *
 * @author Marcel
 */
public abstract class DataPacket extends Packet  {
       
    private Boolean startGame=false;
    
    private Boolean suggestLetterMode=false;
    private String letter;
    
    private Boolean guessWordMode=false;
    private String word;

    public Boolean isStartGame() {
        return startGame;
    }

    public Boolean getSuggestLetterMode() {
        
        return suggestLetterMode;
    }

    public String getLetter() {
        return letter;
    }

    public Boolean getGuessWordMode() {
        return guessWordMode;
    }

    public String getWord() {
        return word;
    }
    
   
    
    public void setStartGame(){
        this.startGame=true;
        this.suggestLetterMode=false;
        this.guessWordMode=false;
    }
    
    public void suggestLetter(String l) throws Exception{
        if(letter.length()!=1){
            throw new Exception("Length must be one");
        }
        this.letter=l;
        
        this.startGame=false;
        this.suggestLetterMode=true;
        this.guessWordMode=false;
    }
    
      public void guessWord(String w) throws Exception{
 
        this.word=w;
        this.startGame=false;
        this.suggestLetterMode=false;
        this.guessWordMode=true;
    }
    
    
    
}
