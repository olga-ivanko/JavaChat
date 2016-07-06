/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.javachat.net;

/**
 *
 * @author homefulloflove
 */
public interface MessageReceiverListener {
    
    /**
     * Trigger when message was received.
     * 
     * @param address sender address.
     * @param message text of message.
     */
    public void messageReceived(String address, String message);
    
}
