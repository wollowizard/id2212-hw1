/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

import id2212.hw1.packets.Packet;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        Socket echoSocket = Session.getInstance().getClientSocket();
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(echoSocket.getOutputStream(), true);
             in = new BufferedReader(new InputStreamReader( echoSocket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       

	BufferedReader stdIn = new BufferedReader(
                                   new InputStreamReader(System.in));
	String userInput;

	out.print(userInput);
	in.readLine();
	
       

    }
}
