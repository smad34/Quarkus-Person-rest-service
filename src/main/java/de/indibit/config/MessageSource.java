package de.indibit.config;

import jakarta.enterprise.context.ApplicationScoped;

import java.text.MessageFormat;
import java.util.ResourceBundle;

@ApplicationScoped
public class MessageSource {

    ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public String getMessage(String key, Object... arguments) {
        return MessageFormat.format(bundle.getString(key), arguments);
    }

}
