package com.fincatto.springvaadin;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, Loggable {

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        getLogger().debug("Sessao criada: " + hse.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        getLogger().debug("Sessao destruida: "+hse.getSession().getId());
    }
}
