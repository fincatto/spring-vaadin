package com.fincatto.springvaadin;

import com.vaadin.flow.server.*;
import org.springframework.stereotype.Component;

@Component
public class SpringVaadinSessionListener extends VaadinServlet implements VaadinServiceInitListener, SessionInitListener, SessionDestroyListener, Loggable {
    
    @Override
    public void serviceInit(final ServiceInitEvent serviceInitEvent) {
        getLogger().debug("Servico inicializado!");
        serviceInitEvent.getSource().addSessionInitListener(this);
        serviceInitEvent.getSource().addSessionDestroyListener(this);
    }
    
    @Override
    public void sessionInit(SessionInitEvent event) {
        getLogger().debug("Sessao criada: " + event.getSession().getSession().getId());
    }
    
    @Override
    public void sessionDestroy(SessionDestroyEvent event) {
        getLogger().debug("Sessao destruida: " + event.getSession().getSession().getId());
    }
}
