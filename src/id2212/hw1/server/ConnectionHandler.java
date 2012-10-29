/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.server;

import id2212.hw1.packets.DataPacket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Marcel
 */
public class ConnectionHandler extends Thread {
    private Socket socket;
    private boolean running;

    public ConnectionHandler(Socket s) {
        this.socket = s;
        this.running = true;
    }

    public void run() {
        ObjectInputStream in;
	ObjectOutputStream out;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }
        try {
            DataPacket data;
            while (running) {
                data = (DataPacket) in.readObject();
                
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        

    }
}
