package by.training.finalproject.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributesListener implements HttpSessionAttributeListener {
    private static final Logger userLogger = LogManager.getLogger(SessionAttributesListener.class);

    @Override
    public void attributeRemoved(HttpSessionBindingEvent ev) {
        userLogger.debug(getMessage(ev, "delete attribute: "));
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent ev) {
        userLogger.debug(getMessage(ev, "add attribute: "));
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent ev) {
        userLogger.debug(getMessage(ev, "replace attribute: "));
    }

    private String getMessage(HttpSessionBindingEvent event, String msg) {
        return msg + event.getName() + " : " + event.getValue();
    }
}
