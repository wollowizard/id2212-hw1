/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

import id2212.hw1.packets.ResponsePacket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfredo
 */
public class Connector extends Thread{
    String ip;
    Integer port;
    Session sess;
            
    public Connector(String ip, Integer port, Session s){
        this.ip=ip;
        this.port=port;
        this.sess=s;
        
    }
    
    @Override
    public void run(){
        
        
        try {
            Socket sock=new Socket(ip, port);
            sess.setClientSocket(sock);
            sess.connectionOk();
        } catch (UnknownHostException ex) {
            
            sess.connectionRefused();
        } catch (IOException ex) {
            
            sess.connectionRefused();
        }
    }
    
}
