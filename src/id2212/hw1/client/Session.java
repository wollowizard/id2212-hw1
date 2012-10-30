/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

import id2212.hw1.packets.DataPacket;
import id2212.hw1.packets.ResponsePacket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;

/**
 *
 * @author alfredo
 */
public class Session extends Observable{
    private Socket clientSocket = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    
    Match currentMatch;
  

    
    private Boolean connected;
    
    public Session(Match g){
        this.currentMatch=g;
    };
    
    public Match getCurrentMatch(){
        return this.currentMatch;
    }
      
    public void setClientSocket(Socket s){
        setChanged();
        this.clientSocket=s;
    }
    
    public Socket getClientSocket(){
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
   


    Boolean isConnected() {
        return this.connected;
    }
    
    public void initiateSession(String ip, String port){
        
        Connector c=new Connector(ip, Integer.parseInt(port), this);
        c.start();
     
    }
    
    void setConnected(boolean b) {
        this.connected=b;
    }
    
   
    
    public void connectionOk(){
        
        setChanged();
        notifyObservers(EventEnum.CONNECTIONOK);
        currentMatch.startGame();
        
    }
    
    public void connectionRefused(){
        this.connected=false;
        setChanged();
        notifyObservers(EventEnum.CONNECTIONREFUSED);
    }
    


    
    
    
    
    
}
