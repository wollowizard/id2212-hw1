/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client;

/**
 *
 * @author Marcel
 */
public class Client {
    public static void main(String args[]){
        Session session=new Session();
        HangmanFrame f=new HangmanFrame(session);
        session.addObserver(f);
        
        f.setVisible(true);
    }
    
    
    
}
