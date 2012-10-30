/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

import id2212.hw1.client.view.HangmanFrame;

/**
 *
 * @author Marcel
 */
public class Client {
    public static void main(String args[]){
        Match newMatch=new Match();
        Session session=new Session(newMatch);
        newMatch.setSession(session);
        HangmanFrame f=new HangmanFrame(session);
        
        session.addObserver(f);
        newMatch.addObserver(f);
        
        f.setVisible(true);
    }
    
    
    
}
