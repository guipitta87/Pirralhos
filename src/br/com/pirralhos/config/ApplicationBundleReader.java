package br.com.pirralhos.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ApplicationBundleReader {
    // Resource bundle for internationalized and accessible text

    private static ResourceBundle bundle = null;

    public static String getString(String key) {
        String value = null;
        try {
            value = getResourceBundle().getString(key);
        } catch (MissingResourceException e) {
            
            System.out.println("java.util.MissingResourceException: Couldn't find value for: " + key);
        }
        if (value == null) {
            value = "Could not find resource: " + key + "  ";
        }
        return value;
    }

    private static ResourceBundle getResourceBundle() {
        if (bundle == null) {
            bundle = ResourceBundle.getBundle("br.com.pirralhos.config.application");
        }
        return bundle;
    }
}