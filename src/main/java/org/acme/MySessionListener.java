package org.acme;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener()
public class MySessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setAttribute("allowedToAccessCKFinder", Boolean.TRUE);
        System.out.println(event.getSession().getAttribute("allowedToAccessCKFinder"));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        // Session destruction logic goes here
    }
}