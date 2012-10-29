/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfredo
 */
public class Connecter extends Thread{
    private String ip;
    private Integer port;
    
    
    public Connecter(String ip, String port){
        Integer portNumber=Integer.parseInt(port);
        this.ip=ip;
 
    }
    
    @Override
    public void run(){
        try {
            Socket clientSocket = new Socket(ip, port);
            Session.getInstance().setClientSocket(clientSocket);
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connecter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Connecter.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    
    }
    
    
}
