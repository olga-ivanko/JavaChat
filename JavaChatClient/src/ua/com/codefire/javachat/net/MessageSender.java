/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.javachat.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author homefulloflove
 */
public class MessageSender {

    private int port;

    public MessageSender(int port) {
        this.port = port;
    }

    private boolean sendMessage(String address, String text) {

        try (Socket client = new Socket(address, port)) {
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            DataInputStream dis = new DataInputStream(client.getInputStream());
            dos.writeUTF(text);
            dos.flush();
            
            if ("SUCCESS".equals(dis.readUTF())) {
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
