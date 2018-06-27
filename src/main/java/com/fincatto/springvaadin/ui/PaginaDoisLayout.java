package com.fincatto.springvaadin.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

@Route("pagina")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class PaginaDoisLayout extends VerticalLayout implements RouterLayout {

    public PaginaDoisLayout() {
        this.add(new RouterLink("Home", MainLayout.class));
    }
}
