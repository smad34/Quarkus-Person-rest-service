package de.indibit.config;

import jakarta.enterprise.context.ApplicationScoped;
import java.text.MessageFormat;
import java.util.ResourceBundle;
/**
 * <b>Title:</b> MessageSource <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@ApplicationScoped
public class MessageSource {

    ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public String getMessage(String key, Object... arguments) {
        return MessageFormat.format(bundle.getString(key), arguments);
    }

}
