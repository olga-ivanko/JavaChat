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
import ua.com.codefire.javachat.Settings;

/**
 *
 * @author homefulloflove
 */
public class MessageSender {

    private int port;

    public MessageSender(int port) {
        this.port = port;
    }

    public boolean sendMessage(String address, String text) {
        String nickname = Settings.getProperty("nickname");

        try (Socket client = new Socket(address, port)) {
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            DataInputStream dis = new DataInputStream(client.getInputStream());
            
            dos.writeUTF("MESSAGE");
            dos.writeUTF(nickname);
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
