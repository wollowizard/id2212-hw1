/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

import id2212.hw1.packets.ResponsePacket;
import java.net.Socket;
import java.util.Observable;

/**
 *
 * @author alfredo
 */
public class Session extends Observable{
    private Socket clientSocket = null;
    
    
    private ResponsePacket lastPacketReceived;
    
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

    void setLastPacket(ResponsePacket packet) {
        this.lastPacketReceived=packet;
    }

    Boolean isConnected() {
        return this.connected;
    }
    
    public void connect(String ip, String port){
        Connector c=new Connector(ip, Integer.parseInt(port), this);
        c.start();
    
    }
    public void connectionOk(){
        this.connected=true;
        setChanged();
        notifyObservers(Event.CONNECTIONOK);
    }
    
    public void connectionRefused(){
        this.connected=false;
        setChanged();
        notifyObservers(Event.CONNECTIONREFUSED);
    }
    
    
    
    
}
