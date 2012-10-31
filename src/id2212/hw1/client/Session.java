/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author alfredo
 */
public class Session extends Observable {

    private Socket clientSocket = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    Match currentMatch;
    private Boolean connected = false;

    public Session(Match g) {
        this.currentMatch = g;
    }

    ;
    
    public Match getCurrentMatch() {
        return this.currentMatch;
    }

    public void setClientSocket(Socket s) {
        setChanged();
        this.clientSocket = s;
    }

    public Socket getClientSocket() {
        return this.clientSocket;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public void initiateSession(String ip, String port) {

        Connector c = new Connector(ip, Integer.parseInt(port), this);
        c.start();

    }

    public void closeSession() {
        if (this.connected) {
            try {
                this.in.close();
                this.out.close();
                this.clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setConnected(boolean b) {
        this.connected = b;
    }
    
    public void setMatch(Match m){
        this.currentMatch=m;
    }

    public Boolean isConnected() {
        return this.connected;
    }

    public void connectionOk() {

        setChanged();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                notifyObservers(EventEnum.CONNECTIONOK);
            }
        });

        currentMatch.startGame();

    }

    public void connectionRefused() {
        this.connected = false;
        setChanged();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Here, we can safely update the GUI
                // because we'll be called from the
                // event dispatch thread
                notifyObservers(EventEnum.CONNECTIONREFUSED);
            }
        });

    }
}
