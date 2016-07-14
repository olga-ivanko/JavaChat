/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.javachat.ui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import ua.com.codefire.javachat.model.Contact;
import ua.com.codefire.javachat.model.Message;
import ua.com.codefire.javachat.net.MessageReceiverListener;
import ua.com.codefire.javachat.net.MessageSender;

/**
 *
 * @author homefulloflove
 */
public class ChatFrame extends javax.swing.JFrame implements MessageReceiverListener {

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat WEEK_FORMAT = new SimpleDateFormat("EEE HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * Describes the info of interlocutor.
     */
    private final Contact contact;
    private final int serverPort;
    private MessageSender sender;

    /**
     * Creates new form ChatFrame
     *
     * @param contact
     * @param serverPort
     * @throws java.io.IOException
     */
    public ChatFrame(Contact contact, int serverPort) throws IOException {
        this.contact = contact;
        this.serverPort = serverPort;

        initNetwork();

        initComponents();

        loadHistory();

        setTitle(contact.toString());

        jtaMessage.requestFocus();
    }

    private void initNetwork() throws IOException {
        sender = new MessageSender(serverPort);
    }

    public Contact getContact() {
        return contact;
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
        jbSend = new javax.swing.JButton();
        jlStatus = new javax.swing.JLabel();
        jmbMain = new javax.swing.JMenuBar();
        jmFile = new javax.swing.JMenu();
        jmiCloseWindow = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setAutoscrolls(true);

        jtaHistory.setEditable(false);
        jtaHistory.setColumns(20);
        jtaHistory.setRows(5);
        jScrollPane1.setViewportView(jtaHistory);

        jSplitPane1.setTopComponent(jScrollPane1);

        jtaMessage.setColumns(20);
        jtaMessage.setRows(5);
        jtaMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtaMessageKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jtaMessage);

        jSplitPane1.setRightComponent(jScrollPane2);

        jbSend.setText("Send");
        jbSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSendActionPerformed(evt);
            }
        });

        jlStatus.setText(" ");

        jmFile.setText("File");

        jmiCloseWindow.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jmiCloseWindow.setText("Close window");
        jmiCloseWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCloseWindowActionPerformed(evt);
            }
        });
        jmFile.add(jmiCloseWindow);
        jmFile.add(jSeparator1);

        jmiExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jmiExit.setText("Exit");
        jmiExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExitActionPerformed(evt);
            }
        });
        jmFile.add(jmiExit);

        jmbMain.add(jmFile);

        setJMenuBar(jmbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbSend)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSend)
                    .addComponent(jlStatus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSendActionPerformed

        sendMessage();

    }//GEN-LAST:event_jbSendActionPerformed

    private void jtaMessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaMessageKeyPressed
        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evt.consume();

            sendMessage();
        }
    }//GEN-LAST:event_jtaMessageKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing


    }//GEN-LAST:event_formWindowClosing

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

        contact.setUnread(0);
        setTitle(contact.toString());

    }//GEN-LAST:event_formWindowActivated

    private void jmiCloseWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCloseWindowActionPerformed

        dispose();

    }//GEN-LAST:event_jmiCloseWindowActionPerformed

    private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExitActionPerformed

        JOptionPane.showMessageDialog(this, "Function isn't implemented.");

    }//GEN-LAST:event_jmiExitActionPerformed

    private void sendMessage() {
        String message = jtaMessage.getText().trim();

        if (!message.isEmpty()) {
            jlStatus.setText(" ");

            // TODO: Validate address and message
            if (sender.sendMessage(contact.getIpAddress(), message)) {
                Message msg = new Message(new Date(), message);
                msg.setIncome(false);
                contact.getMessages().add(msg);
                addHistory(msg.getTimestamp(), "me", msg.getText());
                jtaMessage.setText("");

            } else {
                jlStatus.setText("Message was not sent");
            }
            jtaHistory.setCaretPosition(jtaHistory.getDocument().getLength());
            jtaMessage.requestFocus();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbSend;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JMenu jmFile;
    private javax.swing.JMenuBar jmbMain;
    private javax.swing.JMenuItem jmiCloseWindow;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JTextArea jtaHistory;
    private javax.swing.JTextArea jtaMessage;
    // End of variables declaration//GEN-END:variables

    @Override
    public void messageReceived(String address, String nickname, String message) {
        if (contact.getIpAddress().equals(address)) {
            Message msg = new Message(new Date(), message, isActive());
            contact.getMessages().add(msg);
            addHistory(msg.getTimestamp(), contact.getName(), msg.getText());
        }
    }

    private void addHistory(Date when, String address, String message) {
        long days = Math.abs(TimeUnit.DAYS.convert(new Date().getTime() - when.getTime(), TimeUnit.MILLISECONDS));

        String date;

        if (days < 7) {
            date = TIME_FORMAT.format(when);
        } else if (days < 30) {
            date = WEEK_FORMAT.format(when);
        } else {
            date = DATE_FORMAT.format(when);
        }

        jtaHistory.append(String.format("[%s] %s:\n    %s\n", date, address, message));
    }

    private void loadHistory() {
        for (Message message : contact.getMessages()) {
            String from = message.isIncome() ? contact.getName() : "me";
            addHistory(message.getTimestamp(), from, message.getText());
        }
    }
}
