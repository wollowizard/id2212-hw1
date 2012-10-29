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
    
    private static Session instance=null;
    private ResponsePacket lastPacketReceived;
    
    private Session(){
    };
    
    public static Session getInstance(){
        if(instance==null){
            instance=new Session();
        }
        return instance;
    }
    
    public void setClientSocket(Socket s){
        this.clientSocket=s;
    }
    
    public Socket getClientSocket(){
        return this.clientSocket;
    }

    void setLastPacket(ResponsePacket packet) {
        this.lastPacketReceived=packet;
    }
    
    
    
    
}
