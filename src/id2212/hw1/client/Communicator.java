/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

import id2212.hw1.packets.DataPacket;
import id2212.hw1.packets.Packet;
import id2212.hw1.packets.ResponsePacket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfredo
 */
public class Communicator extends Thread {

    private Packet packet;
    private Session session;

    public Communicator(Packet p, Session s) {
        this.session=s;
        this.packet = p;
    }

    public void run() {
        Socket socket = session.getClientSocket();
        ObjectOutputStream out=session.getOut();
        ObjectInputStream in=session.getIn();
        
        ResponsePacket reply;
        try {
            
            out.writeObject(this.packet);
            out.flush();
            
            try {
                reply= (ResponsePacket) in.readObject();
                System.out.println("Received" + reply.getCurrentWordView() + reply.getWord());
                
                
                session.manageResponsePacket(reply);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
	
       

    }
}
