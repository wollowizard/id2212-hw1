/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author alfredo
 */
public class ClientData {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientData(Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        this.socket = socket;
        
        this.in = in;
        this.out = out;
    }

    
    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }
    
    
}
