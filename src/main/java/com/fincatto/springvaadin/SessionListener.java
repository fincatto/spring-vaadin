package com.fincatto.springvaadin;

import com.vaadin.flow.server.*;

public class SessionListener extends VaadinServlet implements SessionInitListener, SessionDestroyListener, Loggable {

    @Override
    public void sessionInit(SessionInitEvent event) {
        getLogger().debug("Sessao criada: " + event.getSession().getSession().getId());
    }

    @Override
    public void sessionDestroy(SessionDestroyEvent event) {
        getLogger().debug("Sessao destruida: " + event.getSession().getSession().getId());
    }
}
