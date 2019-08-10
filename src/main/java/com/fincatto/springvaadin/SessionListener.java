package com.fincatto.springvaadin;

import com.vaadin.flow.server.*;

import javax.servlet.ServletException;

public class SessionListener extends VaadinServlet implements VaadinServiceInitListener, SessionInitListener, SessionDestroyListener, Loggable {
    
    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        getLogger().debug("Sessao inicializada!");
        getService().addSessionInitListener(this);
        getService().addSessionDestroyListener(this);
    }
    
    @Override
    public void sessionInit(SessionInitEvent event) {
        getLogger().debug("Sessao criada: " + event.getSession().getSession().getId());
    }
    
    @Override
    public void sessionDestroy(SessionDestroyEvent event) {
        getLogger().debug("Sessao destruida: " + event.getSession().getSession().getId());
    }
    
    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        getLogger().debug("Servico inicializado!");
    }
}
