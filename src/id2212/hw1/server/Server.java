/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
        while (listening) {
            Socket clientSocket = serverSocket.accept();
            try {
                ObjectInputStream in;
                ObjectOutputStream out;
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
                ClientData cd = new ClientData(clientSocket, in, out);
                (new ConnectionHandler(cd)).start();

            } catch (IOException e) {
                System.out.println("Error while opening the steams");
                System.out.println(e.toString());
                return;
            }


        }
        serverSocket.close();
    }
}
