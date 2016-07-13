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
package ua.com.codefire.javachat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.com.codefire.javachat.ui.ContactsFrame;

/**
 *
 * @author CodeFireUA <edu@codefire.com.ua>
 */
public final class Settings {

    // SINGLETON
    private static final Settings instance = new Settings();

    static {
        loadSettings();
    }

    public static Settings getInstance() {
        return instance;
    }

    public static Properties loadSettings() {
        try (FileInputStream fis = new FileInputStream("settings.properties")) {
            instance.properties.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

        return instance.properties;
    }

    public static Properties storeSettings() {
        try (FileOutputStream fos = new FileOutputStream("settings.properties")) {
            instance.properties.store(fos, null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

        return instance.properties;
    }

    public static String getProperty(String key) {
        return instance.properties.getProperty(key);
    }

    public static synchronized Object setProperty(String key, String value) {
        return instance.properties.setProperty(key, value);
    }

    public static String getProperty(String key, String defaultValue) {
        return instance.properties.getProperty(key, defaultValue);
    }

    // DATA
    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }
}
