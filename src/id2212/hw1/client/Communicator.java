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

    public Communicator(Packet p) {
        this.packet = p;
    }

    public void run() {
        Socket socket = Session.getInstance().getClientSocket();
        ObjectOutputStream out;
        ObjectInputStream in;
        ResponsePacket reply;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            try {
                reply= (ResponsePacket) in.readObject();
                Session.getInstance().setLastPacket(reply);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
	
       

    }
}
