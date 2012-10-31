/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client.view;

import id2212.hw1.client.EventEnum;
import id2212.hw1.client.Match;
import id2212.hw1.client.Session;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 *
 * @author alfredo
 */
public class HangmanFrame extends javax.swing.JFrame implements Observer {
    
    private GenericPanel panel = null;
    private Session session;
    private static final String TITLE="Hangman1.0 beta";
    private static final String NEWMATCHMESSAGE="Do you really want to start a new match?";
    private static final String NEWMATCHMESSAGETITLE="New match";
    private static final String WINMESSAGE="YOU WIN!";
    private static final String LOSEMESSAGE="YOU LOSE!";
    private static final String CONNECTIONESTABLISHEDMESSAGE="CONNECTION ESTABLISHED";
    private static final String CONNECTIONNOTESTABLISHEDMESSAGE="CONNECTION NOT ESTABLISHED";
    private static final String SERVERDOWNMESSAGE="SERVER IS DOWN. TRY AGAIN LATER";

    /**
     * Creates new form HangmanFrame
     */
    public HangmanFrame(Session session) {
        
        initComponents();
        this.setTitle(TITLE);
        this.session = session;
        this.showConnectionPanel();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        NewGameMenuItem = new javax.swing.JMenuItem();
        ExitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");

        NewGameMenuItem.setText("New game");
        NewGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(NewGameMenuItem);

        ExitMenuItem.setText("Exit");
        ExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(ExitMenuItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NewGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewGameMenuItemActionPerformed
        // TODO add your handling code here:
        
        if (this.session.isConnected()) {
            int result = JOptionPane.showConfirmDialog(this, NEWMATCHMESSAGE, NEWMATCHMESSAGETITLE, JOptionPane.YES_NO_OPTION);
            
            
            if (result == JOptionPane.YES_OPTION) {
                
                //session.closeSession();
                
                Match newMatch = new Match();
               // Session session = new Session(newMatch);
                session.setMatch(newMatch);
                
                newMatch.setSession(session);
                //this.session = session;
                
                //session.addObserver(this);
                newMatch.addObserver(this);
                session.getCurrentMatch().startGame();
                this.showMainPanel();
            }
        }
        
        
    }//GEN-LAST:event_NewGameMenuItemActionPerformed
    
    private void ExitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitMenuItemActionPerformed
        // TODO add your handling code here:
        session.closeSession();
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_ExitMenuItemActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ExitMenuItem;
    private javax.swing.JMenuItem NewGameMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

    public void showConnectionPanel() {
        
        panel = new ConnectionPanel(this.session);
        
        
        this.setContentPane(panel);
        this.validate();
        
    }
    
    public void showMainPanel() {
        
        panel = new MainPanel(session);
        this.setContentPane(panel);
        this.validate();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        
        EventEnum newEvent = (EventEnum) arg;
        if (newEvent == EventEnum.CONNECTIONOK) {
            this.showMainPanel();
            System.out.println(CONNECTIONESTABLISHEDMESSAGE);
        }
        
        
        else if (newEvent == EventEnum.CONNECTIONREFUSED) {
            this.showConnectionPanel();
            JOptionPane.showMessageDialog(this, CONNECTIONNOTESTABLISHEDMESSAGE);
            System.out.println(CONNECTIONNOTESTABLISHEDMESSAGE);
        }
        
        else if (newEvent == EventEnum.GAMERESPONSE) {
            this.panel.updateView(newEvent);
            this.pack();
        }
        
        else  if (newEvent == EventEnum.GAMEOVER) {
            this.panel.updateView(newEvent); 
            this.disablePanel();
            JOptionPane.showMessageDialog(this, LOSEMESSAGE);
            System.out.println(LOSEMESSAGE);
            
        }
        else  if (newEvent == EventEnum.CONGRATULATIONS) {
            this.panel.updateView(newEvent);
            this.disablePanel();
            JOptionPane.showMessageDialog(this, WINMESSAGE);
            System.out.println(WINMESSAGE);
            
        }
        else if (newEvent == EventEnum.SERVERDOWN) {
            this.serverDown();
        }
        
    }
    
    public void serverDown(){
        System.out.println(SERVERDOWNMESSAGE);
            JOptionPane.showMessageDialog(this, SERVERDOWNMESSAGE);
            this.showConnectionPanel();
    
    }
    
    public void disablePanel(){
        Component[] components=this.panel.getComponents();
            for(Component c : components){
                c.setEnabled(false);
            }
    }
}
