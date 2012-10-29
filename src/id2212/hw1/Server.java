/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcel
 */
public class Server {
    
    public static void main(String[] args) throws IOException {
        boolean listening = true;
        ServerSocket serverSocket = null;
        try {
                serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
                System.err.println("Could not listen on port: 1234.");
                System.exit(1);
        }
        while(listening) {
                Socket clientSocket = serverSocket.accept();
                (new ConnectionHandler(clientSocket)).start();
        }
        serverSocket.close();
    }
}
