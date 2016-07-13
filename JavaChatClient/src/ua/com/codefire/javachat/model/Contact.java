/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.javachat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author homefulloflove
 */
public class Contact implements Serializable {

    private String ipAddress;
    private String name;
    private int unread;
    private List<Message> messages = new ArrayList<>();

    public Contact(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Contact(String ipAddress, String name) {
        this.ipAddress = ipAddress;
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }
    
    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String display = (name != null && !name.isEmpty()) ? name : ipAddress;

        if (unread > 0) {
            return String.format("%s (%d)", display, unread);
        } else {
            return String.format("%s", display);
        }
    }

    public void increase(int count) {
        this.unread += count;
    }

}
