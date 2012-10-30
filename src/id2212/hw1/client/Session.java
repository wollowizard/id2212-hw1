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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author alfredo
 */
public class Session extends Observable{
    private Socket clientSocket = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    final Lock lock = new ReentrantLock();
    final Condition packetReceived  = lock.newCondition(); 
    
    
    private String wordView;
    private Integer counter;
    private ResponsePacket lastReply;

    public void setLastReply(ResponsePacket lastReply) {
        this.lastReply = lastReply;
    }
    
    
    private Boolean connected;
    
    public Session(){
        
    };
    
    
    
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
    

    public String getWordView() {
        return wordView;
    }

    public Integer getCounter() {
        return counter;
    }



    Boolean isConnected() {
        return this.connected;
    }
    
    public void connect(String ip, String port){
        
        Connector c=new Connector(ip, Integer.parseInt(port), this);
        c.start();
     
       
    }
    
    public void send(DataPacket p){
        
        Communicator c=new Communicator(p, this);
        c.start();
        
    
    }
    
    public void connectionOk(){
        
        setChanged();
        notifyObservers(Event.CONNECTIONOK);
        
        DataPacket dp=new DataPacket();
        dp.setStartGame();
        
        send(dp);
        
    }
    
    public void connectionRefused(){
        this.connected=false;
        setChanged();
        notifyObservers(Event.CONNECTIONREFUSED);
    }
    
    public ResponsePacket getLastReply(){
        return this.lastReply;
    }
    
    

    public void manageResponsePacket(ResponsePacket reply) {
        
        this.lastReply=reply;
        
        if(reply.isGameMode()){
            this.play(reply);
        }
       
        
    }
    
    public void play(ResponsePacket reply){
        this.wordView=reply.getCurrentWordView();
        this.counter=reply.getFailedAttemptsCounter();
        
        setChanged();
        notifyObservers(Event.GAMERESPONSE);
    }

    void setConnected(boolean b) {
        this.connected=b;
    }
    
    
}
