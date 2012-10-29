/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

import java.net.Socket;

/**
 *
 * @author alfredo
 */
public class Session {
    private Socket clientSocket = null;
    
    private static Session instance=null;
    
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
    
    
    
    
}
