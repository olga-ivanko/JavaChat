/*
 * Copyright (C) 2016 CodeFireUA <edu@codefire.com.ua>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ua.com.codefire.javachat.util;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author CodeFireUA <edu@codefire.com.ua>
 */
public final class Notification {

    public static void play(Type type) {
        switch (type) {
            case INCOMING:
                new Thread(new Player("Ping.aiff")).start();
                break;
            case OUTGOING:
                new Thread(new Player("Pop.aiff")).start();
                break;
        }
    }

    public enum Type {
        INCOMING, OUTGOING, ERROR, WARNING;
    }

    private static class Player implements Runnable {

        private final String sound;

        public Player(String sound) {
            this.sound = sound;
        }

        @Override
        public void run() {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        Notification.class.getResourceAsStream(String.format("/ua/com/codefire/javachat/resources/%s", sound)));
                clip.open(inputStream);
                clip.start();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                System.err.println(e.getMessage());
            }
        }

    }
}
