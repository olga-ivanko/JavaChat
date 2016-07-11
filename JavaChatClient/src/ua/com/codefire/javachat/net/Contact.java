/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.javachat.net;

import java.io.Serializable;

/**
 *
 * @author homefulloflove
 */
public class Contact implements Serializable {

    private String ipAddress;
    private String name;

    public Contact(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Contact(String ipAddress, String name) {
        this.ipAddress = ipAddress;
        this.name = name;
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
        return "[" + ipAddress + ']';
    }

}
