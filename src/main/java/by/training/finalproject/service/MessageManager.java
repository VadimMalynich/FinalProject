package by.training.finalproject.service;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    EN(ResourceBundle.getBundle("langs.labels", new Locale("en", "US"))),
    RU(ResourceBundle.getBundle("langs.labels", new Locale("ru", "RU")));
    private final ResourceBundle bundle;

    MessageManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public static MessageManager getByCode(String str){
        for (MessageManager g : MessageManager.values()) {
            if (g.bundle.getLocale().toString().equals(str)) {
                return g;
            }
        }
        return MessageManager.RU;
    }

    public String getString(String key) {
        return bundle.getString(key);
    }
}

