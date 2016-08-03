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
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private List<MessageReceiverListener> listeners;

    public MessageReceiver(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.serverSocket.setSoTimeout(1000);

        this.listeners = Collections.synchronizedList(new ArrayList<MessageReceiverListener>());
    }

    public boolean addListener(MessageReceiverListener listener) {
        return listeners.add(listener);
    }

    public boolean removeListener(MessageReceiverListener listener) {
        return listeners.remove(listener);
    }

    @Override
    public void run() {
        working = true;

        while (working) {
            try (Socket income = serverSocket.accept()) {
                income.setSoTimeout(1000);
                income.setSoLinger(true, 1000);

                DataInputStream dis = new DataInputStream(income.getInputStream());
                DataOutputStream dos = new DataOutputStream(income.getOutputStream());

                String command = dis.readUTF();

                switch (command) {
                    case "MESSAGE":
                        String nickname = dis.readUTF();
                        String message = dis.readUTF();
//                        System.out.printf("\nMESSAGE:\n  FROM: %s\n  TEXT: %s\n\n", income.getInetAddress().getHostAddress(), message);

                        dos.writeUTF("SUCCESS");
                        dos.flush();

                        for (MessageReceiverListener listener : listeners) {
                            listener.messageReceived(income.getInetAddress().getHostAddress(), nickname, message);
//                            System.out.println(income.getInetAddress().getHostAddress());
                        }
                        break;
                    case "PING":
                        dos.writeUTF("OK");
                        dos.flush();
                        break;
                    default:
                        dos.writeUTF("UNKNOWN COMMAND");
                        dos.flush();
                        break;
                }
            } catch (SocketTimeoutException ex) {
                ; // NOOP
            } catch (IOException ex) {
                Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stop() {
        working = false;
    }

}
