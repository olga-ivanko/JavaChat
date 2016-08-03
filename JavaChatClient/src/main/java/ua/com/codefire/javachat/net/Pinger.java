/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.javachat.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author homefulloflove
 */
public class Pinger implements Runnable {

    private int port;
    private List<PingerListener> listeners;
    private String address;

    public Pinger(String address, int port) {
        this.address = address;
        this.port = port;
        this.listeners = Collections.synchronizedList(new ArrayList<>());
    }

    public boolean add(PingerListener listener) {
        return listeners.add(listener);
    }

    public boolean remove(PingerListener listener) {
        return listeners.remove(listener);
    }

    @Override
    public void run() {
        boolean reachable = false;

        try (Socket client = new Socket()) {
            client.connect(new InetSocketAddress(address, port), 1000);
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            DataInputStream dis = new DataInputStream(client.getInputStream());

            dos.writeUTF("PING");
            dos.flush();

            if ("OK".equals(dis.readUTF())) {
                reachable = true;
            }
        } catch (IOException ex) {
//            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (PingerListener listener : listeners) {
            listener.ping(address, reachable);
        }
    }
}
