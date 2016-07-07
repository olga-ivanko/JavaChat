/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.javachat.ui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.com.codefire.javachat.net.MessageReceiver;
import ua.com.codefire.javachat.net.MessageReceiverListener;
import ua.com.codefire.javachat.net.MessageSender;

/**
 *
 * @author homefulloflove
 */
public class ChatFrame extends javax.swing.JFrame implements MessageReceiverListener {

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    private static final int SERVER_PORT = 5890;

    private MessageReceiver receiver;
    private MessageSender sender;

    /**
     * Creates new form ChatFrame
     *
     * @throws java.io.IOException
     */
    public ChatFrame() throws IOException {
        initNetwork();

        initComponents();
        jtaMessage.requestFocus();
    }

    private void initNetwork() throws IOException {
        receiver = new MessageReceiver(SERVER_PORT);
        receiver.addListener(this);
        new Thread(receiver).start();

        sender = new MessageSender(SERVER_PORT);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaHistory = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaMessage = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jtfAddress = new javax.swing.JTextField();
        jbSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jtaHistory.setEditable(false);
        jtaHistory.setColumns(20);
        jtaHistory.setRows(5);
        jScrollPane1.setViewportView(jtaHistory);

        jSplitPane1.setTopComponent(jScrollPane1);

        jtaMessage.setColumns(20);
        jtaMessage.setRows(5);
        jScrollPane2.setViewportView(jtaMessage);

        jSplitPane1.setRightComponent(jScrollPane2);

        jLabel1.setText("IP: ");

        jtfAddress.setText("127.0.0.1");

        jbSend.setText("Send");
        jbSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 303, Short.MAX_VALUE)
                        .addComponent(jbSend)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSend))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSendActionPerformed

        String address = jtfAddress.getText();
        String message = jtaMessage.getText();

        // TODO: Validate address and message
        if (sender.sendMessage(address, message)) {
            addHistory("me", message);
            jtaMessage.setText("");
        }
        jtaMessage.requestFocus();
    }//GEN-LAST:event_jbSendActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ChatFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbSend;
    private javax.swing.JTextArea jtaHistory;
    private javax.swing.JTextArea jtaMessage;
    private javax.swing.JTextField jtfAddress;
    // End of variables declaration//GEN-END:variables

    @Override
    public void messageReceived(String address, String message) {
        addHistory(address, message);
    }

    private void addHistory(String address, String message) {
        String history = String.format("[%s] %s:\n    %s\n", timeFormat.format(new Date()), address, message);
        jtaHistory.append(history);
    }
}