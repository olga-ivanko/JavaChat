/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.javachat.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author homefulloflove
 */
public class Message implements Serializable{

    private Date timestamp;
    private boolean income;
    private String text;
    private boolean read;

    /**
     * Constructor for outgoing message creation.
     *
     * @param timestamp time when message was sent.
     * @param text message content.
     */
    public Message(Date timestamp, String text) {
        this.timestamp = timestamp;
        this.text = text;
        this.read = true;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public boolean isIncome() {
        return income;
    }

    public String getText() {
        return text;
    }

    public boolean isRead() {
        return read;
    }

    /**
     * Status of the message set as read.
     */
    public void read() {
        this.read = true;
    }

    
}
