/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.server;

import id2212.hw1.packets.DataPacket;
import id2212.hw1.packets.ResponsePacket;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author Marcel
 */
public class ConnectionHandler extends Thread {
    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
        
    private boolean running;
    private static final int NUM_WORD_DIC = 50;
    private static final int NUM_TRY = 5;
    String selectedWord;
    String hiddenWord;
    int intents;

    public ConnectionHandler(ClientData cd) {
        this.socket = cd.getSocket();
        this.in=cd.getIn();
        this.out=cd.getOut();
        this.running = true;
    }

    public void run() {
        
       
        try {
            DataPacket recvData;
            ResponsePacket sendData= new ResponsePacket();
            while (running) {
                System.out.println("waiting for receiving");
                
                recvData = (DataPacket) in.readObject();
                
                System.out.println("received");
                
                if (recvData.isStartGame()) {
                    System.out.println("new");
                    sendData=startNewGame();
                }
                else if (recvData.isSuggestLetterMode()) {
                    sendData=checkLetter(recvData.getLetter());
                }
                else if (recvData.isGuessWordMode()) {
                    sendData=checkWord(recvData.getWord());
                }
                out.writeObject(sendData);
                System.out.println("sent");
                
                if (out != null) {
                    out.flush();
                }
                else {
                    System.out.println("Output is null, no msg sent");
                }
            }
            try {
                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("Error while closing the connection");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }
    
    private ResponsePacket startNewGame() throws FileNotFoundException, IOException {
       
        FileInputStream fs= new FileInputStream("files/dictionary.txt");    
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        ResponsePacket data = new ResponsePacket();
        Random r = new Random();
        int n = r.nextInt(NUM_WORD_DIC);
        for(int i = 0; i < n; ++i) {
            br.readLine();
        }
        selectedWord = br.readLine();
        System.out.println("Word:" + selectedWord);
        hiddenWord = "";
        for (int i = 0; i < selectedWord.length(); ++i) {
            hiddenWord+="_";
        }
        this.intents=this.NUM_TRY;
        data.setGameMode(addSpaces(hiddenWord), this.intents);
        return data;
    }
    
    private String addSpaces(String s) {
        String tmp = "";
        for (int i = 0; i < s.length(); ++i) {
            if (i!=0) tmp+=" ";
            tmp+=s.charAt(i);
        }
        return tmp;
    }
    
    private ResponsePacket checkLetter(String l) {
        System.out.println("We recived letter "+l);
        ResponsePacket data = new ResponsePacket();
        boolean isCorrect = false;
        StringBuilder tmp = new StringBuilder(hiddenWord);
        for (int i = 0; i < this.selectedWord.length();++i) {
            if (this.selectedWord.charAt(i)==l.charAt(0)) {
                tmp.setCharAt(i, l.charAt(0));
                isCorrect = true;
            }
        }
        this.hiddenWord=tmp.toString();
        System.out.println("Server found letter="+isCorrect);
        System.out.println("Sending "+addSpaces(this.hiddenWord));
        if (isCorrect) {
            System.out.println("Number of intents "+this.intents);
            if (this.hiddenWord.contains("_"))
                data.setGameMode(addSpaces(this.hiddenWord), this.intents);
            else 
                data.setCongratulation(this.selectedWord, this.intents);
        }
        else {
            this.intents--;
            if (this.intents<=0) 
                data.setGameOverMode();
            else 
                data.setGameMode(addSpaces(this.hiddenWord), this.intents);
        }
        return data;
    }
    
    private ResponsePacket checkWord(String w) {
        ResponsePacket data = new ResponsePacket();
        System.out.println("Server recived word "+w);
        if (this.selectedWord.equals(w))
            data.setCongratulation(w, this.intents);
        else {
            this.intents--;
            if (this.intents<=0) 
                data.setGameOverMode();
            else 
                data.setGameMode(addSpaces(this.hiddenWord), (this.intents));
        } 
        return data;
    }
}
