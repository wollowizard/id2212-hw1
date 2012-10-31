/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

import id2212.hw1.packets.DataPacket;
import id2212.hw1.packets.ResponsePacket;
import java.util.Observable;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author alfredo
 */
public class Match extends Observable {

    private String wordView;
    private Integer counter;
    private ResponsePacket lastReply;
    private Session session;
    private Integer totalScore;

    public void setSession(Session s) {
        this.session = s;
    }

    public String getWordView() {
        return wordView;
    }

    public Integer getCounter() {
        return counter;
    }

    public ResponsePacket getLastReply() {
        return lastReply;
    }

    public void setWordView(String wordView) {
        this.wordView = wordView;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
    private void setTotalScore(Integer totalScore) {
        this.totalScore=totalScore;
    }

    public void setLastReply(ResponsePacket lastReply) {
        this.lastReply = lastReply;
    }

    void startGame() {

        DataPacket dp = new DataPacket();
        dp.setStartGame();
        sendPacket(dp);
    }

    public void guessALetter(String l) throws Exception {
        DataPacket dp = new DataPacket();

        dp.setLetterToSuggest(l);
        sendPacket(dp);
    }
    
    public void guessWord(String text) {
        DataPacket dp = new DataPacket();
        dp.guessWord(text);
        sendPacket(dp);
    }

    public void manageResponsePacket(ResponsePacket reply) {
        System.out.println("manage response : " + Thread.currentThread());
        this.setLastReply(reply);
        setWordView(reply.getCurrentWordView());
        setCounter(reply.getFailedAttemptsCounter());
        setTotalScore(reply.getTotalScore());
        setChanged();
        
        if (reply.isGameMode()) {
        
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    notifyObservers(EventEnum.GAMERESPONSE);
                }
            });

        } else if (reply.isGameOverMode()) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    notifyObservers(EventEnum.GAMEOVER);

                }
            });
            
        
        }
        else if(reply.isCongratulationsMode()){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    notifyObservers(EventEnum.CONGRATULATIONS);

                }
            });
        
        }
    }

    public void sendPacket(DataPacket p) {

        Communicator c = new Communicator(p, session);
        c.start();
    }

   

    
}
