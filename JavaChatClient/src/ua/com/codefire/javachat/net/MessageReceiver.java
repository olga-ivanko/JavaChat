/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.javachat.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author homefulloflove
 */
public class MessageReceiver implements Runnable {

    private int port;
    private ServerSocket serverSocket;
    private boolean working;

    public MessageReceiver(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.serverSocket.setSoTimeout(1000);
    }

    @Override
    public void run() {
        working = true;

        while (working) {
            try {
                Socket income = serverSocket.accept();
                DataInputStream dis = new DataInputStream(income.getInputStream());
                DataOutputStream dos = new DataOutputStream(income.getOutputStream());

                String command = dis.readUTF();

                switch (command) {
                    case "MESSAGE":
                        String message = dis.readUTF();
                        System.out.printf("\nMESSAGE:\n  FROM: %s\n  TEXT: %s\n\n", income.getInetAddress().getHostAddress(), message);
                        
                        dos.writeUTF("SUCCESS");
                        dos.flush();
                        break;
                    default:
                        dos.writeUTF("UNKNOWN COMMAND");
                        dos.flush();
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
