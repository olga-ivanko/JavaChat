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
public interface PingerListener {

    public void ping(String adress, boolean reachable);

}
