/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

import id2212.hw1.packets.DataPacket;
import id2212.hw1.packets.ResponsePacket;
import java.util.Observable;

/**
 *
 * @author alfredo
 */
public class Match extends Observable {

    private String wordView;
    private Integer counter;
    private ResponsePacket lastReply;
    private Session session;

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

    public void manageResponsePacket(ResponsePacket reply) {

        this.setLastReply(reply);

        if (reply.isGameMode()) {
            setWordView(reply.getCurrentWordView());
            setCounter(reply.getFailedAttemptsCounter());

            setChanged();
            notifyObservers(EventEnum.GAMERESPONSE);
        }


    }

    public void sendPacket(DataPacket p) {

        Communicator c = new Communicator(p, session);
        c.start();

    }
}
