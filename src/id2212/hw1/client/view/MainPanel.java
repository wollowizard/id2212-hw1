/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id2212.hw1.client.view;

import id2212.hw1.client.EventEnum;
import id2212.hw1.client.Session;
import id2212.hw1.packets.ResponsePacket;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author alfredo
 */
public class MainPanel extends GenericPanel {
    private Session session;
    private static final String LETTERERRORMESSAGE="Please insert one letter only";
    private static final String PATTERN="^[A-Za-z0-9]$";
    

    /**
     * Creates new form MainPanel
     */
    public MainPanel(Session s) {
        this.session=s;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        currentWordLabel = new javax.swing.JLabel();
        letterTextField = new javax.swing.JTextField();
        guessLetterButton = new javax.swing.JButton();
        counterLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        totalScoreLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        guessWordTextField = new javax.swing.JTextField();
        guessWordButton = new javax.swing.JButton();

        currentWordLabel.setText("____________");

        letterTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                letterTextFieldKeyPressed(evt);
            }
        });

        guessLetterButton.setText("Guess letter");
        guessLetterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guessLetterButtonActionPerformed(evt);
            }
        });

        counterLabel.setText("0");

        jLabel1.setText("Total score: ");

        totalScoreLabel.setText("0");

        jLabel3.setText("Remaining attempts:");

        jLabel4.setText("letter:");

        jLabel2.setText("Guess word:");

        guessWordButton.setText("Guess word");
        guessWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guessWordButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(currentWordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(letterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(counterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(guessWordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(guessLetterButton)
                            .addComponent(guessWordButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentWordLabel)
                    .addComponent(letterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(guessLetterButton))
                .addGap(18, 18, 18)
                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(guessWordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guessWordButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(counterLabel)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalScoreLabel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void guessLetterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guessLetterButtonActionPerformed
        try {
            // TODO add your handling code here:
            
            session.getCurrentMatch().guessALetter(this.letterTextField.getText().toLowerCase());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, LETTERERRORMESSAGE);
        }
 
    }//GEN-LAST:event_guessLetterButtonActionPerformed

    private void letterTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_letterTextFieldKeyPressed
        char keyChar = evt.getKeyChar();
        String s=String.valueOf(keyChar);
        Pattern p = Pattern.compile(PATTERN);
        if(p.matcher(s).matches()){
            errorLabel.setText("");
            letterTextField.setText("");
        }
        else{
            letterTextField.setText("");
            errorLabel.setText(LETTERERRORMESSAGE);
        }
        
    }//GEN-LAST:event_letterTextFieldKeyPressed

    private void guessWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guessWordButtonActionPerformed
        // TODO add your handling code here:
        
        session.getCurrentMatch().guessWord(this.guessWordTextField.getText().toLowerCase());
        
    }//GEN-LAST:event_guessWordButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel counterLabel;
    private javax.swing.JLabel currentWordLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton guessLetterButton;
    private javax.swing.JButton guessWordButton;
    private javax.swing.JTextField guessWordTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField letterTextField;
    private javax.swing.JLabel totalScoreLabel;
    // End of variables declaration//GEN-END:variables


    @Override
    public void updateView(EventEnum e) {
        
        if(e==EventEnum.GAMERESPONSE){
            ResponsePacket lastReply = session.getCurrentMatch().getLastReply();
            this.currentWordLabel.setText(lastReply.getCurrentWordView());
            this.counterLabel.setText(session.getCurrentMatch().getLastReply().getFailedAttemptsCounter().toString());
            this.totalScoreLabel.setText(session.getCurrentMatch().getLastReply().getTotalScore().toString());
            
        }
        
    }
}
